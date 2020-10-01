package br.com.ricardo.reposgithub.presentation.repolist.viewmodel

import androidx.lifecycle.*
import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepoViewModel(private val repository: RepoRepository) : ViewModel() {

    companion object {
        private const val VIEW_FLIPPER_REPOS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    private val _repoListMutableLiveData = MutableLiveData<List<Repo>>()
    val repoList : LiveData<List<Repo>>
    get() = _repoListMutableLiveData

    private val _viewFlipperMutableLiveData = MutableLiveData<Pair<Int, Int?>>()
    val viewFlipper : LiveData<Pair<Int, Int?>>
    get() = _viewFlipperMutableLiveData




    fun getRepoList() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repository.fetchRepoList { repoResult: RepoListResult ->
                    when(repoResult) {
                        is RepoListResult.Success -> {
                            _repoListMutableLiveData.postValue(repoResult.repoList)
                            _viewFlipperMutableLiveData.postValue(Pair(VIEW_FLIPPER_REPOS, null))
                        }
                        else -> {
                            _viewFlipperMutableLiveData.postValue(Pair(VIEW_FLIPPER_ERROR, 123))
                        }
                    }
                }
            }
        }


//        CoroutineScope(Dispatchers.Main).launch {
//            val repos = withContext(Dispatchers.Default) {
//                repository.fetchRepoList()
//            }
//
//            when(repos as RepoListResult) {
//                is RepoListResult.Success -> {
//                    _repoListMutableLiveData.value = repos
//                    _viewFlipperMutableLiveData.value = Pair(VIEW_FLIPPER_REPOS, null)
//                }
//                else -> {
//                    _viewFlipperMutableLiveData.value = Pair(VIEW_FLIPPER_ERROR, 123)
//                }
//            }
//
//        }

    }







    class ViewModelFactory(private val repository: RepoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RepoViewModel(repository) as T
        }

    }

}