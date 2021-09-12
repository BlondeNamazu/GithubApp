package com.example.githubapp

import javax.inject.Inject

class GithubUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun getUserRepositoryList(userName: String): Result<List<GithubRepositoryResponse>> =
        githubRepository.getUserRepositoryList(userName)
}