package br.com.ricardo.reposgithub.data.remote

import br.com.ricardo.reposgithub.data.response.RepoGitHubBodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoGitHubService {

    @GET("/search/repositories")
    suspend fun getRepositories(@Query("page") page: Int,
                                @Query("q") language: String = "language:kotlin",
                                @Query("sort") sort: String = "stars")
            : Response<RepoGitHubBodyResponse>

}