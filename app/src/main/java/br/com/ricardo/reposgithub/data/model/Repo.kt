package br.com.ricardo.reposgithub.data.model

import java.io.Serializable

data class Repo(
    val name : String,
    val stars : Int,
    val forks : Int,
    val owner : OwnerInfo
) : Serializable