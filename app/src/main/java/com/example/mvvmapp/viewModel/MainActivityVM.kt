package com.example.mvvmapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityVM: ViewModel() {

    private var testLiveData =  MutableLiveData<String>()
    var str = "Bangladesh"

    fun test() : String {
        Log.d("test","test is working")
        return "test is working"
    }

    fun test2() : MutableLiveData<String>? {
        //val str = "Bangladesh"
        /*if (testLiveData == null) {
            Log.d("test2LD1","Invoking new MutableLiveData")
            testLiveData = MutableLiveData()
        }*/
        str = "Bangladeshiiiiiiiiiiiiiiii"
        testLiveData?.value = str
        Log.d("test2LD2","test is working: $str and ld: $testLiveData")
        return testLiveData
    }
    fun test3(s: String) : MutableLiveData<String>? {
        testLiveData.postValue(s)
        Log.d("test2LD2","test is working: $s and ld: $testLiveData")
        return testLiveData
    }

    fun setName() {
        str = "Bangladeshiiiiiiiiiiiiiiii"
        //testLiveData.value = str
        Log.d("test2","test value set with $str")
    }

    fun getName(): String {
        Log.d("test3","test value get: ${testLiveData.value}")
        //return testLiveData
        return str
    }


}