package br.com.ricardo.reposgithub.data

import br.com.ricardo.reposgithub.data.model.Repo

sealed class RepoListResult {
    class Success(val repoList: List<Repo>) : RepoListResult()
    class ApiError(val statusCode: Int) : RepoListResult()
    object ServerError : RepoListResult()
}