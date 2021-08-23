package com.example.githubapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}/repos)")
    suspend fun getUserRepositoryList(
        @Path("username") username: String
    ): List<Response<GithubRepositoryResponse>>

}