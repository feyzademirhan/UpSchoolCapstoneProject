package com.feyza.applicationecommerce.utils

import com.feyza.applicationecommerce.database.retrofit.ItemInterface
import com.feyza.applicationecommerce.database.retrofit.RetrofitInstance


class ApiUtils {
    companion object{
        fun getProductsInterface(): ItemInterface {
            return RetrofitInstance.getClient().create(ItemInterface::class.java)
        }
    }
}