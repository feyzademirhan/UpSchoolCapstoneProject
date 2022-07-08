package com.feyza.applicationecommerce.database.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.feyza.applicationecommerce.database.model.ItemModel
import com.feyza.applicationecommerce.database.repository.ItemRepository

class ItemViewModel(context: Context): ViewModel() {
    private var productsRepo= ItemRepository()

    private var _itemList= MutableLiveData<List<ItemModel>>()
    val itemList: LiveData<List<ItemModel>>
        get()=_itemList

    private var _bagProduct=MutableLiveData<List<ItemModel>>()
    val bagProduct:LiveData<List<ItemModel>>
    get()=_bagProduct

    init{
        getProducts()
        _bagProduct=productsRepo.itemList
    }


    private fun getProducts(){
        productsRepo.products()
        _itemList=productsRepo.itemList
    }

    fun addToBag(
         user:String,
         title:String,
         price:Double,
         description:String,
         category:String,
         image:String,
         rate: Float,
         count:Int,
         sale_state:Int){
        productsRepo.addToBag(user,title,price,description,category,image,rate,count,sale_state)
     }


}