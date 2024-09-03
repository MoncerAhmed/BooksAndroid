package nl.ahmed.common.kotlin.operation

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import nl.ahmed.common.kotlin.templates.Mapper
import nl.ahmed.common.kotlin.operation.models.OperationException
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.common.kotlin.templates.Dao
import nl.ahmed.common.kotlin.templates.Model
import nl.ahmed.common.kotlin.utils.Logger
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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

    @MockK
    private lateinit var logger: Logger

    private lateinit var cashedFetchOperationExecutor: CashedFetchOperationExecutor<Model.Dto, Model.Entity, Model.Domain>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        cashedFetchOperationExecutor = CashedFetchOperationExecutor(
            insertDao = insertDoa,
            queryDao = queryDao,
            deleteDao = deleteDao,
            dtoToEntityMapper = dtoToEntityMapper,
            entityToDomainMapper = entityToDomainMapper,
            logger = logger
        )

        every { logger.logError(throwable = any(), message = any()) } returns Unit
    }

    @Test
    fun `Given the api operation is failing and query dao is also failing, When executing the operation, Check that OperationException is thrown`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } throws Exception()
            coEvery { queryDao.getAll() } throws Exception()

            // When
            val response = cashedFetchOperationExecutor.execute(apiOperation)

            // Then
            assert(response is OperationResult.Failure)
            assert((response as OperationResult.Failure).throwable is OperationException)
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 0) { deleteDao.deleteAll() }
            coVerify(exactly = 0) { insertDoa.insert(any<List<Model.Entity>>()) }
            verify(exactly = 2) { logger.logError(throwable = any(), message = any()) }
        }

    @Test
    fun `Given the api operation is failing and query dao is not failing, When executing the operation, Check returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            val thrownExceptions = Exception()
            coEvery { apiOperation() } throws thrownExceptions
            coEvery { queryDao.getAll() } returns mockk()

            val expectedData = mockk<List<Model.Domain>>()
            every { entityToDomainMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 0) { deleteDao.deleteAll() }
            coVerify(exactly = 0) { insertDoa.insert(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDomainMapper(any()) }
            verify(exactly = 1) { logger.logError(throwable = thrownExceptions, message = any()) }

        }

    @Test
    fun `Given the api operation is not failing, When executing the operation, Check local storage is cleared, new items are inserted, and returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } returns mockk()
            coEvery { queryDao.getAll() } returns mockk()
            coEvery { deleteDao.deleteAll() } returns Unit
            coEvery { insertDoa.insert(any<List<Model.Entity>>()) } returns Unit

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Domain>>()
            every { entityToDomainMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 1) { deleteDao.deleteAll() }
            coVerify(exactly = 1) { insertDoa.insert(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDomainMapper(any()) }
            coVerify(exactly = 1) { dtoToEntityMapper(any()) }
        }

    @Test
    fun `Given the api operation is not failing and updating the local storage is also not failing, When executing the operation, Check local storage is cleared, new items are inserted, and returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } returns mockk()
            coEvery { queryDao.getAll() } returns mockk()
            coEvery { deleteDao.deleteAll() } returns Unit
            coEvery { insertDoa.insert(any<List<Model.Entity>>()) } returns Unit

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Domain>>()
            every { entityToDomainMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 1) { deleteDao.deleteAll() }
            coVerify(exactly = 1) { insertDoa.insert(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDomainMapper(any()) }
            coVerify(exactly = 1) { dtoToEntityMapper(any()) }
        }

    @Test
    fun `Given the api operation is not failing but updating the local storage is failing, When executing the operation, Check error is logged, and returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } returns mockk()
            coEvery { queryDao.getAll() } returns mockk()
            val thrownException = Exception()
            coEvery { deleteDao.deleteAll() } throws thrownException // Failing local storage operation
            coEvery { insertDoa.insert(any<List<Model.Entity>>()) } returns Unit

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Domain>>()
            every { entityToDomainMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 0) { queryDao.getAll() }
            coVerify(exactly = 1) { deleteDao.deleteAll() }
            coVerify(exactly = 0) { insertDoa.insert(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDomainMapper(any()) }
            coVerify(exactly = 1) { dtoToEntityMapper(any()) }
            verify(exactly = 1) { logger.logError(throwable = thrownException, message = any()) }
        }
}
