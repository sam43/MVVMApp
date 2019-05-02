package com.example.mvvmapp.repositories

import com.example.mvvmapp.models.Demo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {

    /*@GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>
*/
    @GET("api/json/get/ceOpxZpzYi?indent=2")
    fun getDemoResponse(): Call<Demo>

}