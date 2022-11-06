package com.works.foodorderapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.data.repo.FoodsRepository
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var urepo: UserRepository, var frepo: FoodsRepository): ViewModel() {
    var currentUser = MutableLiveData<FirebaseUser>()
    var userName = MutableLiveData<String>()
    var cartCount = MutableLiveData<Int>()
    var cartTotal = MutableLiveData<Int>()
    var orderList = MutableLiveData<List<Cart>>()


    init {
        getUser()
    }

    fun getUser(){
        currentUser.value = urepo.getUser()
        userName.value = currentUser.value?.email.toString().substringBefore('@')
    }

    fun signOut(){
        urepo.signOut()
        getUser()
    }

    fun updateCart(cart: Cart){
        var quantity = 0
        CoroutineScope(Dispatchers.Main).launch {
            try {
                frepo.loadCart(userName.value).filter { it.food_name == cart.food_name }.forEach {
                    quantity += it.food_quantity
                    frepo.deleteFromCart(it.cart_food_id, userName.value)
                }

                frepo.addToCart(cart.food_name, cart.food_image_name, cart.food_price, quantity, userName.value!!)
            }
            catch(e:Exception){

            }
        }
    }

    fun getCartCount(){
        var count = 0
        CoroutineScope(Dispatchers.Main).launch {
            try {
                frepo.loadCart(userName.value).forEach {
                    count += it.food_quantity
                }
            }
            catch(e:Exception){
                count = 0
            }
            cartCount.value = count
        }
    }

    fun getCartTotal(){
        var total = 0
        CoroutineScope(Dispatchers.Main).launch {
            try {
                frepo.loadCart(userName.value).forEach {
                    total += it.food_quantity * it.food_price
                }
            }
            catch(e:Exception){
                total = 0
            }
            cartTotal.value = total
        }
    }
}