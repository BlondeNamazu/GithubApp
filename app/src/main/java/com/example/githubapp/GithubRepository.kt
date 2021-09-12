package com.example.githubapp

import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubApi: GithubApi
) : RepositoryType {
    //    suspend fun getUserRepositoryList(userName: String): Result<List<GithubRepositoryEntity>> {
//        val results = githubApi.getUserRepositoryList(userName).map { response ->
//            when {
//                response.isSuccessful -> Result.success(response.body()!!)
//                else -> Result.failure(Throwable())
//            }
//        }
//        return if (results.all { it.isSuccess }) {
//            Result.success(results.map { it.getOrThrow().toEntity() })
//        } else Result.failure(Throwable())
//    }
    suspend fun getUserRepositoryList(userName: String): Result<List<GithubRepositoryResponse>> {
        return execute {
            githubApi.getUserRepositoryList(userName)
        }
    }
}