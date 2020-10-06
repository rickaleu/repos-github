package br.com.ricardo.reposgithub.utils

import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.presentation.repolist.repository.RepoRepository

class MockRepository(private val result: RepoListResult) : RepoRepository {

    override suspend fun fetchRepoList(page: Int, repoResultCallBack: (result: RepoListResult) -> Unit) {
        repoResultCallBack(result)
    }

}