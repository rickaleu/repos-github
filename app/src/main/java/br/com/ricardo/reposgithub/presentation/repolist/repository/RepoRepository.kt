package br.com.ricardo.reposgithub.presentation.repolist.repository

import br.com.ricardo.reposgithub.data.RepoListResult
import br.com.ricardo.reposgithub.data.model.Repo

interface RepoRepository {

    suspend fun fetchRepoList(page: Int, repoResultCallBack: (result: RepoListResult) -> Unit)
}