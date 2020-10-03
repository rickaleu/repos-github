package br.com.ricardo.reposgithub.data.remote

import br.com.ricardo.reposgithub.data.response.RepoGitHubBodyResponse
import retrofit2.Response
import retrofit2.http.GET

interface RepoGitHubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars&page=1")
    suspend fun getRepositories() : Response<RepoGitHubBodyResponse>

}