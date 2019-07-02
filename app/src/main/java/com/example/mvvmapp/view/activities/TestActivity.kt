package com.example.mvvmapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.R
import com.example.mvvmapp.viewModel.TestActivityVM
import com.sam43.customtextview.LoggingClass
import kotlinx.android.synthetic.main.activity_test_activity.*
import org.jetbrains.anko.startActivity
import androidx.lifecycle.Observer
import com.example.mvvmapp.databinding.ActivityTestActivityBinding
import com.example.mvvmapp.models.Demo


class TestActivity : AppCompatActivity() {

    private lateinit var testVM: TestActivityVM
    private var amount = 0
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testVM = ViewModelProviders.of(this@TestActivity).get(TestActivityVM::class.java)
        amount = intent.getIntExtra("amount", 500)
        val binding : ActivityTestActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_test_activity)
        binding.name = "Your name"
        binding.lastName = "Your last name"
        button.setOnClickListener {
            //startActivity<ListActivity>()
           observeViewModel(testVM)
        }
    }

    override fun onResume() {
        super.onResume()
        count++
        LoggingClass.info("onResume amount : $amount and count: $count")
    }

    private fun observeViewModel(viewModel: TestActivityVM) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this,
            Observer<Demo> { data ->
                if (data != null) {
                    //…
                    //projectAdapter.setProjectList(projects)
                    //tvResponse.text = data.toString()
                    Log.d("Demo", "value: ${data.uData?.get(0)?.type}")
                } else {
                    Log.d("Demo", "value: is getting null")
                }
            })
    }
}
