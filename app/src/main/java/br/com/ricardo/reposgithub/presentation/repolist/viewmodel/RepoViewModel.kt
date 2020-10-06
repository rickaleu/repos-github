package br.com.ricardo.reposgithub.presentation.repolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ricardo.reposgithub.R
import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepository
import br.com.ricardo.reposgithub.utils.RepoGitHubConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoViewModel(private val repository: RepoRepository) : ViewModel() {

    companion object {
        private const val VIEW_FLIPPER_REPOS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    private val _repoListMutableLiveData: MutableLiveData<List<Repo>> = MutableLiveData()
    val repoList: LiveData<List<Repo>>
        get() = _repoListMutableLiveData

    private val _viewFlipperMutableLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()
    val viewFlipper: LiveData<Pair<Int, Int?>>
        get() = _viewFlipperMutableLiveData


    fun getRepoList(page: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repository.fetchRepoList(page) { repoResult: RepoListResult ->
                    when (repoResult) {
                        is RepoListResult.Success -> {
                            _repoListMutableLiveData.postValue(repoResult.repoList)
                            _viewFlipperMutableLiveData.postValue(Pair(VIEW_FLIPPER_REPOS, null))
                        }
                        is RepoListResult.ApiError -> {
                            if (repoResult.statusCode == RepoGitHubConstants.HTTP_ERROR_UNPROCESSABLE_ENTITY) {
                                _viewFlipperMutableLiveData.postValue(
                                    Pair(VIEW_FLIPPER_ERROR, R.string.repo_view_flipper_error_422))
                            } else {
                                _viewFlipperMutableLiveData.postValue(
                                    Pair(VIEW_FLIPPER_ERROR, R.string.repo_view_flipper_error_500))
                            }
                        }
                    }
                }
            }
        }
    }

}