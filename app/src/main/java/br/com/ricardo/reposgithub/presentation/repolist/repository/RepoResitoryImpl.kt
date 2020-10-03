package br.com.ricardo.reposgithub.presentation.repolist.repository

import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.Repo
import br.com.ricardo.reposgithub.data.remote.RetrofitClient
import br.com.ricardo.reposgithub.data.response.RepoGitHubBodyResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoResitoryImpl : RepoRepository {

    private val service = RetrofitClient.SERVICE

    override suspend fun fetchRepoList(repoResultCallBack: (result: RepoListResult) -> Unit) {

        var resultRepoList = mutableListOf<Repo>()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getRepositories()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            for (item in it.repoResponseItems) {
                                val result = item.getRepoModel()
                                resultRepoList.add(result)
                            }
                            repoResultCallBack.invoke(RepoListResult.Success(resultRepoList))
                        }
                    }

                } catch (e: Exception) {
                    repoResultCallBack.invoke(RepoListResult.ApiError(response.code()))
                }
            }
        }
    }
}