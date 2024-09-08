package nl.ahmed.common.kotlin.operation

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import nl.ahmed.templates.kotlin.data.Mapper
import nl.ahmed.common.kotlin.operation.models.OperationException
import nl.ahmed.common.kotlin.operation.models.OperationResult
import nl.ahmed.templates.kotlin.data.Dao
import nl.ahmed.templates.kotlin.data.Model
import nl.ahmed.common.kotlin.utils.Logger
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CashedFetchAllOperationExecutorTest {

    @MockK
    private lateinit var dtoToEntityMapper: Mapper<List<Model.Dto>, List<Model.Entity>>

    @MockK
    private lateinit var entityToDataMapper: Mapper<List<Model.Entity>, List<Model.Data>>

    @MockK
    private lateinit var updateDao: Dao.Update<Model.Entity>

    @MockK
    private lateinit var queryDao: Dao.Query<Model.Entity>

    @MockK
    private lateinit var logger: Logger

    private lateinit var cashedFetchAllOperationExecutor: CashedFetchAllOperationExecutor<Model.Dto, Model.Entity, Model.Data>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        cashedFetchAllOperationExecutor = CashedFetchAllOperationExecutor(
            updateDao = updateDao,
            queryDao = queryDao,
            dtoToEntityMapper = dtoToEntityMapper,
            entityToDataMapper = entityToDataMapper,
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
            val response = cashedFetchAllOperationExecutor.execute(apiOperation)

            // Then
            assert(response is OperationResult.Failure)
            assert((response as OperationResult.Failure).throwable is OperationException)
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 0) { updateDao.updateAll(any<List<Model.Entity>>()) }
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

            val expectedData = mockk<List<Model.Data>>()
            every { entityToDataMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchAllOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 0) { updateDao.updateAll(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDataMapper(any()) }
            verify(exactly = 1) { logger.logError(throwable = thrownExceptions, message = any()) }

        }

    @Test
    fun `Given the api operation is not failing, When executing the operation, Check local storage is cleared, new items are inserted, and returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } returns mockk()
            coEvery { queryDao.getAll() } returns mockk()
            coEvery { updateDao.updateAll(any<List<Model.Entity>>()) } returns Unit

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Data>>()
            every { entityToDataMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchAllOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 1) { updateDao.updateAll(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDataMapper(any()) }
            coVerify(exactly = 1) { dtoToEntityMapper(any()) }
        }

    @Test
    fun `Given the api operation is not failing and updating the local storage is also not failing, When executing the operation, Check local storage is cleared, new items are inserted, and returns domain items`() =
        runTest {
            // Given
            val apiOperation = mockk<suspend () -> List<Model.Dto>>()
            coEvery { apiOperation() } returns mockk()
            coEvery { queryDao.getAll() } returns mockk()
            coEvery { updateDao.updateAll(any<List<Model.Entity>>()) } returns Unit

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Data>>()
            every { entityToDataMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchAllOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 1) { queryDao.getAll() }
            coVerify(exactly = 1) { updateDao.updateAll(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDataMapper(any()) }
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
            coEvery { updateDao.updateAll(any<List<Model.Entity>>()) } throws thrownException // Failing local storage operation

            every { dtoToEntityMapper(any()) } returns mockk()

            val expectedData = mockk<List<Model.Data>>()
            every { entityToDataMapper(any()) } returns expectedData
            val expectedResponse = OperationResult.Success(expectedData)

            // When
            val response = cashedFetchAllOperationExecutor.execute(apiOperation)

            // Then
            assertEquals(
                /* expected = */ expectedResponse,
                /* actual = */ response
            )
            coVerify(exactly = 1) { apiOperation() }
            coVerify(exactly = 0) { queryDao.getAll() }
            coVerify(exactly = 0) { updateDao.updateAll(any<List<Model.Entity>>()) }
            coVerify(exactly = 1) { entityToDataMapper(any()) }
            coVerify(exactly = 1) { dtoToEntityMapper(any()) }
            verify(exactly = 1) { logger.logError(throwable = thrownException, message = any()) }
        }
}
