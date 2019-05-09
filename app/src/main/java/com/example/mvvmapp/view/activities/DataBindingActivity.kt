package com.example.mvvmapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmapp.R
import com.example.mvvmapp.databinding.ActivityDataBindingBinding
import com.example.mvvmapp.viewModel.DataBindingActivityVM
import org.jetbrains.anko.toast

class DataBindingActivity : AppCompatActivity() {

    private lateinit var dataBindVM: DataBindingActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        dataBindVM = ViewModelProviders.of(this).get(DataBindingActivityVM::class.java)
        DataBindingUtil.setContentView<ActivityDataBindingBinding>(
            this,
            R.layout.activity_data_binding
        ).apply {
            this.lifecycleOwner = this@DataBindingActivity
            this.viewmodel = dataBindVM
        }
        dataBindVM.editTextContent.observe(this, Observer {
            toast(it)
        })
    }
}
