package com.example.mvvmapp.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.R
import com.example.mvvmapp.viewModel.MainActivityVM
import com.sam43.customtextview.LoggingClass
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainActivityVM
    private var liveValue = "Default value"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoggingClass.error("Here is the error...from my own library")
        mainViewModel = ViewModelProviders.of(this@MainActivity).get(MainActivityVM::class.java)
        setContentView(R.layout.activity_main)
        //mainViewModel.setName()
        Toast.makeText(this@MainActivity, mainViewModel.test(), Toast.LENGTH_LONG).show()

        tvTest.text = liveValue

        //liveValue = "Bangladesh is my country"
        mainViewModel.getName()?.observe(this@MainActivity, Observer { str ->
            //liveValue = str
            Log.d("state1", "Value: $str")
            tvTest.text = "OnStateChanged $str"
            Toast.makeText(this@MainActivity, str, Toast.LENGTH_LONG).show()
        })
        /*Handler().postDelayed({
            Log.d("stestRun","test is working: $liveValue")
        }, 3000)*/

        btnCheck.setOnClickListener {
            liveValue = etInputField.text.toString()
            mainViewModel.setName(liveValue)
            Log.d("state2", "Value: $liveValue")
            //Toast.makeText(this@MainActivity, str, Toast.LENGTH_LONG).show()
            tvTest.text = "OnButtonClicked $liveValue"
        }

        btnCheck1.setOnClickListener {
            startActivity(Intent(this@MainActivity, ListActivity::class.java))
        }

        btnCheck2.setOnClickListener {
            /*startActivity<TestActivity>(
                "id" to 5,
                "amount" to 2500
        )*/
            startActivity<DataBindingActivity>()
        }
    }
}
