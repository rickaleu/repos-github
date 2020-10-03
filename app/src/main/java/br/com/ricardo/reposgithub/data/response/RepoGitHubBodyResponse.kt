package br.com.ricardo.reposgithub.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoGitHubBodyResponse(
    @Json(name = "items")
    val repoResponseItems: List<RepoGitHubItemResponse>
)