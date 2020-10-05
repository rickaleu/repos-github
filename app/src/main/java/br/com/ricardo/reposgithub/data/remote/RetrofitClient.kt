package br.com.ricardo.reposgithub.data.remote

import br.com.ricardo.reposgithub.utils.RepoGitHubConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private lateinit var retrofit: Retrofit

    private fun initRetrofit() : Retrofit {
        val client = OkHttpClient.Builder()

        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(RepoGitHubConstants.BASE_URL)
            .client(client.build())
            .build()

        return retrofit
    }

    val SERVICE : RepoGitHubService = initRetrofit().create(RepoGitHubService::class.java)
}