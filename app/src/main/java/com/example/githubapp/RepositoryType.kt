package com.example.githubapp

import retrofit2.HttpException
import retrofit2.Response

interface RepositoryType {
    suspend fun <T> execute(target: suspend () -> Response<T>): Result<T> {
        return try {
            val responseResult = target.invoke()
            if (responseResult.isSuccessful) {
                Result.success(responseResult.body()!!)
            } else {
                throw HttpException(responseResult)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}