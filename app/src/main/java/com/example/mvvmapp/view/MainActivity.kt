package com.example.mvvmapp.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.R
import com.example.mvvmapp.viewModel.MainActivityVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainActivityVM
    private var liveValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityVM::class.java)
        setContentView(R.layout.activity_main)
        //mainViewModel.setName()
        Toast.makeText(this@MainActivity, mainViewModel.test(), Toast.LENGTH_LONG).show()
        btnCheck.setOnClickListener {
            mainViewModel.test3("Bangladesh is my country")?.observe(this@MainActivity, Observer {str ->
                liveValue = str
                tvTest.text = str
            })
            /*liveValue = mainViewModel.getName()
            tvTest.text = mainViewModel.getName()*/

            Log.d("test2","test is working: $liveValue")
        }
    }
}
