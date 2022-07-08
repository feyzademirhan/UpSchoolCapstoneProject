package com.feyza.applicationecommerce.database.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.feyza.applicationecommerce.database.model.CRUDResponse
import com.feyza.applicationecommerce.database.model.ItemModel
import com.feyza.applicationecommerce.database.retrofit.ItemInterface
import com.feyza.applicationecommerce.utils.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemRepository {
    var itemList= MutableLiveData<List<ItemModel>>()

    var categoryList=MutableLiveData<List<String>>()

    var bagProductList=MutableLiveData<List<ItemModel>>()

    private val retrofitInstance: ItemInterface = ApiUtils.getProductsInterface()


    fun products(){
        retrofitInstance.getItemsData().enqueue(object : Callback<List<ItemModel>?> {
            override fun onResponse(
                call: Call<List<ItemModel>?>,
                response: Response<List<ItemModel>?>
            ) {
                response.body()?.let {
                    itemList.value=it
                }
            }

            override fun onFailure(call: Call<List<ItemModel>?>, t: Throwable) {
            }
        })
    }

    fun addToBag(user:String,title:String,price:Double,description:String,category:String,image:String,rate:Float,count:Int,sale_state:Int){
        retrofitInstance.addToBag(user,title,price,description,category,image,rate,count,sale_state).enqueue(object : Callback<CRUDResponse?> {
            override fun onResponse(call: Call<CRUDResponse?>, response: Response<CRUDResponse?>) {
                response.body()?.let {
                    Log.v("item repository",it.message)
                }
            }

            override fun onFailure(call: Call<CRUDResponse?>, t: Throwable) {
                Log.v("item repository",t.message.orEmpty())
            }
        })
    }

    fun getBagProducts(user: String){
        retrofitInstance.getBagProducts(user).enqueue(object : Callback<List<ItemModel>?> {
            override fun onResponse(
                call: Call<List<ItemModel>?>,
                response: Response<List<ItemModel>?>
            ) {
                response.body()?.let {
                    bagProductList.value=it
                }
            }

            override fun onFailure(call: Call<List<ItemModel>?>, t: Throwable) {
            }
        })
    }

    fun categories(){
        retrofitInstance.getCategories().enqueue(object : Callback<List<String>?> {
            override fun onResponse(
                call: Call<List<String>?>,
                response: Response<List<String>?>
            ) {
                response.body().let {
                    categoryList.value=it
                }

            }

            override fun onFailure(call: Call<List<String>?>, t: Throwable) {
                Log.v("Categories","${t.message}")
            }
        })
    }



}