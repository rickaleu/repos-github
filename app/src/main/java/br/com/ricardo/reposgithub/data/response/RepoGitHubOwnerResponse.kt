package br.com.ricardo.reposgithub.data.response

import br.com.ricardo.reposgithub.data.model.OwnerInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RepoGitHubOwnerResponse (
    @Json(name = "login")
    val login: String,
    @Json(name = "avatar_url")
    val avatar: String
) {
    fun getOwnerInfoModel() = OwnerInfo(
        login = this.login,
        avatar = this.avatar
    )
}