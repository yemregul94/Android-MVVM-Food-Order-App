package com.works.foodorderapplication.data.datasource

import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.data.entity.Foods
import com.works.foodorderapplication.data.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource(var fdao:FoodsDao) {

    suspend fun loadFoods() : List<Foods> =
        withContext(Dispatchers.IO){
            fdao.loadFoods().foods
    }

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, userName:String) =
        fdao.addToCart(foodName, foodImageName, foodPrice, foodQuantity, userName)


    suspend fun loadCart(userName: String?) : List<Cart> =
        withContext(Dispatchers.IO){
            fdao.loadCart(userName).carts
        }

    suspend fun deleteFromCart(cartFoodID: Int?, userName: String?) = fdao.deleteFromCart(cartFoodID, userName)
}