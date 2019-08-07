package com.example.mvvmapp.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapp.models.Demo
import com.example.mvvmapp.models.ServerResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProjectRepository {
    private var service: APIService
    private var projectRepository: ProjectRepository? = null

    private var BASE_URL = "http://test.prefeex.com/api/"
    //private var BASE_URL = "http://192.168.1.73:8080/api/"

    init {
        /*val intercept = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)*/
        /*val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(intercept)
            .build()*/
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(okHttpClient)
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

    fun uploadFile(cxt: Context, multipartMap: MultipartBody.Part): LiveData<ServerResponse> {
        val data = MutableLiveData<ServerResponse>()
        service.uploadFile(multipartMap).enqueue(object : Callback<ServerResponse> {
            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                Toast.makeText(cxt, "Failed to upload file", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                data.value = response.body()
                val r = response.body()
                Log.d("*****Uploaded", "Location: ${r?.responseData?.responseResults?.get(0)?.responseLocation}")
                Toast.makeText(
                    cxt,
                    "code: ${response.code()} \nmessage: ${r?.responseMessage?.responseEn} and \n Location: ${r?.responseData?.responseResults?.get(
                        0
                    )?.responseLocation}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        return data
    }
}