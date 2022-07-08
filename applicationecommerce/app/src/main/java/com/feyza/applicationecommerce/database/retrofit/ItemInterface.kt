package com.feyza.applicationecommerce.database.retrofit

import com.feyza.applicationecommerce.database.model.CRUDResponse
import com.feyza.applicationecommerce.database.model.ItemModel
import retrofit2.Call
import retrofit2.http.*

interface ItemInterface {

    @GET("get_products.php")
    fun getItemsData(): Call<List<ItemModel>>

    @GET("get_categories.php")
    fun getCategories(): Call<List<String>>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field(" rate") rate: Float,
        @Field("count") count: Int,
        @Field("sale_state") sale_state: Int
    ): Call<CRUDResponse>

    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProducts(
        @Field("user") user: String
    ): Call<List<ItemModel>>

}