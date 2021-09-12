package com.example.githubapp

import javax.inject.Inject

class GithubUsecase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun getUserRepositoryList(userName: String): Result<List<GithubRepositoryEntity>> =
        githubRepository.getUserRepositoryList(userName)
}