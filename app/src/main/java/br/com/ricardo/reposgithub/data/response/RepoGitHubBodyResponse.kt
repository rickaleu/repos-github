package br.com.ricardo.reposgithub.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoGitHubBodyResponse(
    @Json(name = "total_count")
    val totalCount : Int,
    @Json(name = "items")
    val repoResponseItems: List<RepoGitHubItemResponse>
)