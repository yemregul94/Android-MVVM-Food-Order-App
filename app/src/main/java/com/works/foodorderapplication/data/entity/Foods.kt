package com.works.foodorderapplication.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Foods (@SerializedName("yemek_id") var food_id:Int,
                  @SerializedName("yemek_adi") var food_name:String,
                  @SerializedName("yemek_resim_adi") var food_image_name:String,
                  @SerializedName("yemek_fiyat") var food_price:Int
        ) : Serializable