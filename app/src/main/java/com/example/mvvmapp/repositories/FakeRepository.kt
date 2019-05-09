package com.example.mvvmapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object FakeRepository {
    private val fruitNames: List<String> = listOf(
        "Apple", "Banana", "Orange", "Kiwi", "Grapes", "Fig",
        "Pear", "Strawberry", "Gooseberry", "Raspberry", "Guava"
    )
    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName

    init {
        _currentRandomFruitName.value = fruitNames.first()
    }

    fun getRandomFruitName(): String {
        val rand = Random()
        return fruitNames[rand.nextInt(fruitNames.size)]
    }

    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }
}