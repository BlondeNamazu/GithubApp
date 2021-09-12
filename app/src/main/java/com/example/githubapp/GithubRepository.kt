package com.example.githubapp

import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApi: GithubApi
) : RepositoryType {
    suspend fun getUserRepositoryList(userName: String): Result<List<GithubRepositoryEntity>> {
        return execute {
            githubApi.getUserRepositoryList(userName)
        }.map { list -> list.map { it.toEntity() } }
    }
}