package com.example.githubapp

import android.net.Uri

data class GithubRepositoryEntity(
    val id: Long,
    val name: String,
    val ownerAvatarUrl: Uri,
    val description: String,
    val htmlUrl: Uri,
    val apiUrl: Uri
)