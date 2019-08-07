package com.example.mvvmapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmapp.R
import com.example.mvvmapp.models.Demo
import com.google.gson.Gson
import com.sam43.customtextview.LoggingClass
import com.example.mvvmapp.models.Demo.Student



class MainTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        val std = Student(name = "Midway")
        val strStd = Gson().toJson(std)
        //val intoModel = Gson().

        val student = Gson().fromJson(strStd, Student::class.java)
        val strStd2 = Gson().toJson(student)

        LoggingClass.debug("model class : $std // $strStd // $strStd2")



    }
}
