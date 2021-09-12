package com.example.githubapp

import android.net.Uri

data class GithubRepositoryEntity(
    val id: Long,
    val name: String,
    val htmlUrl: Uri,
    val apiUrl: Uri
)