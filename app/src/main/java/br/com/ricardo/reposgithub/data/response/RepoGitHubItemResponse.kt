package br.com.ricardo.reposgithub.data.response

import br.com.ricardo.reposgithub.data.model.Repo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoGitHubItemResponse(
    @Json(name = "name")
    val name : String,
    @Json(name = "stargazers_count")
    val stars : Int,
    @Json(name = "forks_count")
    val forks : Int,
    @Json(name = "owner")
    val owner : RepoGitHubOwnerResponse
) {
    fun getRepoModel() = Repo(
        name = this.name,
        stars = this.stars,
        forks = this.forks,
        owner = this.owner.getOwnerInfoModel()
    )
}