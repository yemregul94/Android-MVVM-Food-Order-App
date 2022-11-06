package com.works.foodorderapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(var frepo: FoodsRepository) : ViewModel(){
    var cartList = MutableLiveData<List<Cart>>()
    var userName = MutableLiveData<String>()
    lateinit var incomingCart: Cart


    fun addToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int, userName:String) {
        incomingCart = Cart(1, foodName, foodImageName, foodPrice, foodQuantity, userName)

        CoroutineScope(Dispatchers.Main).launch {
            frepo.addToCart(foodName, foodImageName, foodPrice, foodQuantity, userName)
            loadCart()
        }
    }

    fun loadCart(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartList.value = frepo.loadCart(userName.value)
            }
            catch(e:Exception){
                cartList.value = emptyList()
            }
        }
    }

    fun buttonIncrease(quantity: String): Int{
        return if(quantity != ""){
            quantity.toInt() + 1
        } else{
            1
        }
    }

    fun buttonDecrease(quantity: String): Int{
        return if(quantity != ""){
            quantity.toInt() - 1
        } else{
            1
        }
    }


    fun checkQuantity(quantity: String): String{
        return if(quantity != ""){
            if(quantity.toInt() <= 0){
                "1"
            } else if(quantity.toInt() > 999){
                "999"
            }else{
                quantity
            }
        }else{
            ""
        }
    }

    fun updateTotal(quantity: String, price: Int): Int{
        return if(quantity != ""){
            quantity.toInt() * price
        }else{
            price
        }
    }
}