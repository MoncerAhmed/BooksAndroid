package nl.ahmed.common.kotlin.operation

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import nl.ahmed.common.kotlin.Mapper
import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.common.kotlin.templates.Model
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class CashedFetchOperationExecutorTest {

    @MockK
    private lateinit var dtoToEntityMapper: Mapper<List<Model.Dto>, List<Model.Entity>>

    @MockK
    private lateinit var entityToDomainMapper: Mapper<List<Model.Entity>, List<Model.Domain>>

    @MockK
    private lateinit var insertDoa: Dao.Insert<Model.Entity>

    @MockK
    private lateinit var queryDao: Dao.Query<Model.Entity>

    @MockK
    private lateinit var deleteDao: Dao.Delete<Model.Entity>

    private lateinit var cashedFetchOperationExecutor: CashedFetchOperationExecutor<Model.Dto, Model.Entity, Model.Domain>

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        cashedFetchOperationExecutor = CashedFetchOperationExecutor(
            insertDao = insertDoa,
            queryDao = queryDao,
            deleteDao = deleteDao,
            dtoToEntityMapper = dtoToEntityMapper,
            entityToDomainMapper = entityToDomainMapper
        )
    }

    @Test
    fun `Given the api operation is failing and query dao is also failing, When executing the operation, Check that OperationException is thrown`() = runTest {
        // Given
        val apiOperation = mockk<suspend () -> List<Model.Dto>>()
        coEvery { apiOperation() } throws Exception()

        coEvery { queryDao.getAll() } throws Exception()

        // Then
        assertThrows<OperationException> {
            runBlocking {
                // When
                cashedFetchOperationExecutor.execute(apiOperation)
            }
        }
        coVerify(exactly = 1) { apiOperation() }
        coVerify(exactly = 1) { queryDao.getAll() }
        coVerify(exactly = 0) { deleteDao.deleteAll() }
        coVerify(exactly = 0) { insertDoa.insert(any<List<Model.Entity>>()) }
    }

    @Test
    fun `Given the api operation is failing and query dao is not failing, When executing the operation, Check returns domain items`() = runTest {
        // Given
        val apiOperation = mockk<suspend () -> List<Model.Dto>>()
        coEvery { apiOperation() } throws Exception()
        coEvery { queryDao.getAll() } returns mockk()

        val expectedResponse = mockk<List<Model.Domain>>()
        every { entityToDomainMapper(any()) } returns expectedResponse

        // When
        assertDoesNotThrow {
            runBlocking {
                val response = cashedFetchOperationExecutor.execute(apiOperation)
                assertEquals(
                    expected = expectedResponse,
                    actual = response
                )
            }
        }

        // Then
        coVerify(exactly = 1) { apiOperation() }
        coVerify(exactly = 1) { queryDao.getAll() }
        coVerify(exactly = 0) { deleteDao.deleteAll() }
        coVerify(exactly = 0) { insertDoa.insert(any<List<Model.Entity>>()) }
        coVerify(exactly = 1) { entityToDomainMapper(any()) }
    }

    @Test
    fun `Given the api operation is not failing, When executing the operation, Check local storage is cleared, new items are inserted, and returns domain items`() = runTest {
        // Given
        val apiOperation = mockk<suspend () -> List<Model.Dto>>()
        coEvery { apiOperation() } returns mockk()
        coEvery { queryDao.getAll() } returns mockk()
        coEvery { deleteDao.deleteAll() } returns Unit
        coEvery { insertDoa.insert(any<List<Model.Entity>>()) } returns Unit

        every { dtoToEntityMapper(any()) } returns mockk()

        val expectedResponse = mockk<List<Model.Domain>>()
        every { entityToDomainMapper(any()) } returns expectedResponse

        // When
        assertDoesNotThrow {
            runBlocking {
                val response = cashedFetchOperationExecutor.execute(apiOperation)
                assertEquals(
                    expected = expectedResponse,
                    actual = response
                )
            }
        }

        // Then
        coVerify(exactly = 1) { apiOperation() }
        coVerify(exactly = 1) { queryDao.getAll() }
        coVerify(exactly = 1) { deleteDao.deleteAll() }
        coVerify(exactly = 1) { insertDoa.insert(any<List<Model.Entity>>()) }
        coVerify(exactly = 1) { entityToDomainMapper(any()) }
        coVerify(exactly = 1) { dtoToEntityMapper(any()) }
    }
}
