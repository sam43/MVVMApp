package com.example.mvvmapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mvvmapp.interfaces.Constants
import com.example.mvvmapp.models.Demo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import androidx.lifecycle.MutableLiveData


class ProjectRepository {
    private var service: APIService
    private var projectRepository: ProjectRepository? = null

    init {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.HTTPS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(APIService::class.java)
    }

    @Synchronized
    fun getInstance(): ProjectRepository {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (projectRepository == null) {
            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
        }
        return projectRepository!!
    }

    fun getDemoData(): LiveData<Demo> {
        val data = MutableLiveData<Demo>()
        service.getDemoResponse().enqueue(object : Callback<Demo>{
            override fun onFailure(call: Call<Demo>, t: Throwable) {
                data.value = null
                Log.d("Demo","onFail: ${t.message}")
            }

            override fun onResponse(call: Call<Demo>, response: Response<Demo>) {
                data.value = response.body()
            }

        })
        return data
    }
}