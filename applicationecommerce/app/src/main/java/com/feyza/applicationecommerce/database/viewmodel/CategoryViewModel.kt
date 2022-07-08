package com.feyza.applicationecommerce.database.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyza.applicationecommerce.database.repository.ItemRepository

class CategoryViewModel(context: Context):ViewModel() {
    private var productsRepo= ItemRepository()
    private var _categoryList= MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>>
        get()=_categoryList
    init {
        getCategories()
    }
    private fun getCategories(){
        productsRepo.categories()
        _categoryList=productsRepo.categoryList
    }
}