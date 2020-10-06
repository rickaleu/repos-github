package br.com.ricardo.reposgithub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.ricardo.reposgithub.data.remote.RepoGitHubService
import br.com.ricardo.reposgithub.data.response.RepoGitHubBodyResponse
import br.com.ricardo.reposgithub.data.response.RepoGitHubItemResponse
import br.com.ricardo.reposgithub.data.response.RepoGitHubOwnerResponse
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepositoryImpl
import br.com.ricardo.reposgithub.utils.ManagedCoroutineScope
import br.com.ricardo.reposgithub.utils.TestScope
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RepoRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val testScope = TestCoroutineScope(testDispatcher)

    private val service = mock<RepoGitHubService>()

    private val currenciesRemoteImpl = RepoRepositoryImpl()

    private val managedCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    @Before
    fun before() {
        Dispatchers.setMain(testDispatcher)

    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }


    @Test
    suspend fun `when getRepositories is called, then returns data`() {

        val responseBody = RepoGitHubBodyResponse(
            totalCount = 3256985,
            repoResponseItems = listOf(
                RepoGitHubItemResponse(
                    name = "repo-test",
                    stars = 1254,
                    forks = 542,
                    RepoGitHubOwnerResponse(
                        login = "Ricardo",
                        avatar = "http://.."
                    )
                )
            )
        )

        whenever(service.getRepositories(1, "language:kotlin", "stars"))
            .thenCallRealMethod()

        managedCoroutineScope.launch {
            withContext(testDispatcher) {
                val response = currenciesRemoteImpl.fetchRepoList(1, anyOrNull())
                Assert.assertEquals(response, responseBody)
            }
        }
    }

    @Test
    suspend fun `when getRepositories is called, then assert not null`() {

        whenever(service.getRepositories(1, "language:kotlin", "stars"))
            .thenCallRealMethod()

        managedCoroutineScope.launch {
            withContext(testDispatcher) {
                val response = currenciesRemoteImpl.fetchRepoList(1, anyOrNull())
                Assert.assertNotNull(response)
            }
        }
    }



}