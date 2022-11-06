package com.works.foodorderapplication.data.entity

import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @SerializedName("yemekler") var foods: List<Foods>,
    var success: Int
)