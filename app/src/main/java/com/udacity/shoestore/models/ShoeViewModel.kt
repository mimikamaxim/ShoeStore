package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    val shoe = Shoe("", "", "", "")

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = originShoeList()
    }

    fun addShoeToList(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
    }

    private fun originShoeList(): MutableList<Shoe> {
        return (mutableListOf(
            Shoe("Sneakers", "Reebok", "38-48", "Good quality, fashion design"),
            Shoe("Boots", "Centro", "36-48", "Cheep and fascinating"),
            Shoe("Gumshoes","MiniMi","Any","New height quality brand!")
        ))
    }
}
