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
class FoodCartViewModel @Inject constructor(var frepo: FoodsRepository) : ViewModel() {
    var cartList = MutableLiveData<List<Cart>>()
    var sourceCartList = MutableLiveData<List<Cart>>()
    var userName = MutableLiveData<String>()

    fun getUser(){
        loadCart(userName.value)
    }

    fun loadCart(userName: String?){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartList.value = frepo.loadCart(userName)
            }
            catch(e:Exception){
                cartList.value = emptyList()
            }
            getCartTotal()
            sourceCartList.value = cartList.value
        }
    }

    fun sortCart(criteria: String){
        CoroutineScope(Dispatchers.Main).launch {
            when(criteria){
                "idAsc" -> cartList.value = cartList.value?.sortedBy { it.cart_food_id }
                "idDesc" -> cartList.value = cartList.value?.sortedByDescending { it.cart_food_id }
                "nameAsc" -> cartList.value = cartList.value?.sortedBy { it.food_name }
                "nameDesc" -> cartList.value = cartList.value?.sortedByDescending { it.food_name }
                "priceAsc" -> cartList.value = cartList.value?.sortedBy { it.food_price }
                "priceDesc" -> cartList.value = cartList.value?.sortedByDescending { it.food_price }
                "qtyAsc" -> cartList.value = cartList.value?.sortedBy { it.food_quantity }
                "qtyDesc" -> cartList.value = cartList.value?.sortedByDescending { it.food_quantity }
                "totalAsc" -> cartList.value = cartList.value?.sortedBy { it.food_price * it.food_quantity }
                "totalDesc" -> cartList.value = cartList.value?.sortedByDescending { it.food_price * it.food_quantity }
                else -> loadCart(userName.value)
            }
        }
    }

    fun searchCart(query: String){
        CoroutineScope(Dispatchers.Main).launch {
            cartList.value = sourceCartList.value?.filter { it.food_name.contains(query, true) }
        }
    }

    fun deleteFromCart(cartFoodID: Int?, userName: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.deleteFromCart(cartFoodID, userName)
            loadCart(userName)
        }
    }

    fun getCartTotal(): String{
        var total = 0
        cartList.value?.forEach {
            total += it.food_quantity * it.food_price
        }
        return total.toString()
    }

    fun clearCart(){
        cartList.value?.forEach {
            deleteFromCart(it.cart_food_id, userName.value)
        }
    }
}