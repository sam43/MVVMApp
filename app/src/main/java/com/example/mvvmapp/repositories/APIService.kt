package com.example.mvvmapp.repositories

import com.example.mvvmapp.models.Demo
import com.example.mvvmapp.models.ServerResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface APIService {

    /*@GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>
*/
    @GET("api/json/get/ceOpxZpzYi?indent=2")
    fun getDemoResponse(): Call<Demo>

    @GET("/data/2.5/{movie_id}/getDetails")
    fun getMovieDatils(@Path("movie_id") movieID: String): Call<Demo>


    @Multipart
    @POST("upload")
    fun uploadFile(
        @Part map: MultipartBody.Part
    ): Call<ServerResponse>

}