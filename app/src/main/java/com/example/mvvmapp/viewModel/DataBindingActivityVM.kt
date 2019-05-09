package com.example.mvvmapp.viewModel

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapp.repositories.FakeRepository

class DataBindingActivityVM(application: Application) : AndroidViewModel(application) {
    val currentRandomFruitName: LiveData<String>
        get() = FakeRepository.currentRandomFruitName

    fun onChangeRandomFruitClick() = FakeRepository.changeCurrentRandomFruitName()

    @Bindable
    val editTextContent = MutableLiveData<String>()
    val _displayedEditTextContent = MutableLiveData<String>()
    val displayedEditTextContent: LiveData<String>
        get() = _displayedEditTextContent

    fun onClickDisplayContent() {
        _displayedEditTextContent.value = editTextContent.value
    }

    fun onSelectRandomEditTextFruit() {
        editTextContent.value = FakeRepository.getRandomFruitName()
    }

}