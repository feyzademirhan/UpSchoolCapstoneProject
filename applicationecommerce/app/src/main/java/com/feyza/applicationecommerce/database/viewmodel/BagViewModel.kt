package com.feyza.applicationecommerce.database.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyza.applicationecommerce.database.model.ItemModel
import com.feyza.applicationecommerce.database.repository.ItemRepository


class BagViewModel(context: Context):ViewModel() {
    private var bagRepo=ItemRepository()
    private var _bagList=MutableLiveData<List<ItemModel>>()
    val bagList:LiveData<List<ItemModel>>
    get()=_bagList
    init {
        _bagList=bagRepo.bagProductList
    }
    fun getBagProducts(user:String){
        bagRepo.getBagProducts(user)
        _bagList=bagRepo.bagProductList
    }
}