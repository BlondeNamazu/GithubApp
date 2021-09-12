package com.example.githubapp

import android.net.Uri
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoryResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("html_url") val htmlUrl: String,
    @SerialName("url") val apiUrl: String,
) {
    fun toEntity(): GithubRepositoryEntity = GithubRepositoryEntity(
        id = id,
        name = name,
        htmlUrl = Uri.parse(htmlUrl),
        apiUrl = Uri.parse(apiUrl)
    )
}