package br.com.ricardo.reposgithub.data.model

data class Repo(
    val name : String,
    val stars : Int,
    val forks : Int,
    val owner : OwnerInfo
)