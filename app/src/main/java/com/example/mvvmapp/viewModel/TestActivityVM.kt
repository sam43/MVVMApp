package com.example.mvvmapp.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvmapp.models.Demo
import com.example.mvvmapp.models.ServerResponse
import com.example.mvvmapp.repositories.ProjectRepository
import okhttp3.MultipartBody


class TestActivityVM(application: Application) : AndroidViewModel(application) {

    private var projectListObservable: LiveData<Demo>? = null
    init {
        projectListObservable = ProjectRepository().getInstance().getDemoData()
    }
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getProjectListObservable(): LiveData<Demo> {
        return projectListObservable!!
    }

    fun uploadFile2Server(cxt: Context, map: MultipartBody.Part): LiveData<ServerResponse> {
        return ProjectRepository().getInstance().uploadFile(cxt, map)
    }
}