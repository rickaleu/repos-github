package br.com.ricardo.reposgithub.presentation.repolist.repository

import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RepoResitoryImpl : RepoRepository {

    override suspend fun fetchRepoList(repoResultCallBack: (result: RepoListResult) -> Unit): List<Repo> {
        val resultRepoList = withContext(Dispatchers.Default) {
            delay(3000)
            listOf(
                Repo(1, "repositorio 1"),
                Repo(2, "repositorio 2"),
                Repo(3, "repositorio 3")
            )
        }

        repoResultCallBack.invoke(RepoListResult.Success(resultRepoList))

        return resultRepoList
    }


}