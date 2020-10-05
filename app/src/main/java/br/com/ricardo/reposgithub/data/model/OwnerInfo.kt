package br.com.ricardo.reposgithub.data.model

import java.io.Serializable

data class OwnerInfo (
    val login: String,
    val avatar: String
) : Serializable