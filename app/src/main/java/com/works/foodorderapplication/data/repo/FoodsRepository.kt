package com.works.foodorderapplication.data.repo

import com.works.foodorderapplication.data.datasource.FoodsDataSource
import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.data.entity.Foods

class FoodsRepository(var fds: FoodsDataSource) {
    suspend fun loadFoods() : List<Foods> = fds.loadFoods()

    suspend fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, userName:String) =
        fds.addToCart(foodName, foodImageName, foodPrice, foodQuantity, userName)

    suspend fun loadCart(userName: String?) : List<Cart> = fds.loadCart(userName)

    suspend fun deleteFromCart(cartFoodID: Int?, userName: String?) = fds.deleteFromCart(cartFoodID, userName)

}