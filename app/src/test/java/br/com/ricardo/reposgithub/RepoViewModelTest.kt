package br.com.ricardo.reposgithub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.OwnerInfo
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.presentation.repolist.viewmodel.RepoViewModel
import br.com.ricardo.reposgithub.utils.MockRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepoViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RepoViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var repoLiveDataObserver: Observer<List<Repo>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>



    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `call getRepoList with liveData success`() {

        val repo = listOf(
            Repo("repo-test", 45854, 458547, OwnerInfo("Ricardo", "http://..."))
        )
        val resultSuccess = MockRepository(RepoListResult.Success(repo))

        viewModel = RepoViewModel(resultSuccess)
        viewModel.repoList.observeForever(repoLiveDataObserver)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)


        viewModel.getRepoList(1)

        verify(repoLiveDataObserver).onChanged(repo)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `call getRepoList with liveData empty`() {
        val resultSuccess = MockRepository(RepoListResult.Success(listOf()))

        viewModel = RepoViewModel(resultSuccess)
        viewModel.repoList.observeForever(repoLiveDataObserver)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)

        viewModel.getRepoList(1)

        verify(repoLiveDataObserver).onChanged(listOf())
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `call getRepoList with liveData api error`() {
        val statusCode = 422
        val message = R.string.repo_view_flipper_error_422
        val resultApiError = MockRepository(RepoListResult.ApiError(statusCode))

        viewModel = RepoViewModel(resultApiError)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)

        viewModel.getRepoList(1)

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, message))
    }

    @Test
    fun `call getRepoList with liveData server error`() {
        val statusCode = 500
        val message = R.string.repo_view_flipper_error_500
        val resultApiError = MockRepository(RepoListResult.ApiError(statusCode))

        viewModel = RepoViewModel(resultApiError)
        viewModel.viewFlipper.observeForever(viewFlipperLiveDataObserver)

        viewModel.getRepoList(1)

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, message))
    }
}