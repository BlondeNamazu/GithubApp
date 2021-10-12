package com.example.githubapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerResponse(
    @SerialName("id") val id: Long,
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val owner_avatar_url: String,
)
