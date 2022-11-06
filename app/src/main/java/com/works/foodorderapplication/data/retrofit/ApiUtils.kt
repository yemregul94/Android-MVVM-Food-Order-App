package com.works.foodorderapplication.data.retrofit

import com.works.foodorderapplication.utils.BASE_URL

class ApiUtils {
    companion object{
        fun getFoodsDao(): FoodsDao{
            return RetrofitClient.getClient(BASE_URL).create(FoodsDao::class.java)
        }
    }
}