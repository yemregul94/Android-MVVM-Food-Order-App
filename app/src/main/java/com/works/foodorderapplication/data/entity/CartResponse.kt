package com.works.foodorderapplication.data.entity

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("sepet_yemekler") var carts: List<Cart>,
    var success: Int
)