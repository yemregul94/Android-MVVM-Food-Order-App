package com.works.foodorderapplication.ui.main.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.works.foodorderapplication.R
import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.data.entity.Foods
import com.works.foodorderapplication.databinding.RowCartsBinding
import com.works.foodorderapplication.ui.main.view.FoodCartFragmentDirections
import com.works.foodorderapplication.ui.main.view.FoodListFragmentDirections
import com.works.foodorderapplication.ui.main.viewmodel.FoodCartViewModel
import com.works.foodorderapplication.utils.BASE_URL

class FoodCartAdapter(var mContext: Context,
                      var cartList: List<Cart>,
                      var viewModel: FoodCartViewModel) : RecyclerView.Adapter<FoodCartAdapter.ViewHolder>() {

    inner class ViewHolder(binding: RowCartsBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: RowCartsBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowCartsBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_carts, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = cartList.get(position)
        val bind = holder.binding
        bind.cartObject = cart

        val food = Foods(0, cart.food_name, cart.food_image_name, cart.food_price)

        val url = "$BASE_URL/yemekler/resimler/${cart.food_image_name}"
        Glide.with(bind.ivCartImage).load(url).override(200, 200).into(bind.ivCartImage)

        bind.ivDelete.setOnClickListener {
            Snackbar.make(it,"${cart.food_quantity} adet ${cart.food_name} silinsin mi?", Snackbar.LENGTH_LONG).setActionTextColor(Color.CYAN)
                .setAction("EVET"){
                    viewModel.deleteFromCart(cart.cart_food_id, cart.user_name)
                    Snackbar.make(it,"${cart.food_quantity} adet ${cart.food_name} silindi", Snackbar.LENGTH_SHORT).show()
                }.show()
        }

        bind.RowCard.setOnClickListener {
            val nav = FoodCartFragmentDirections.cartToDetails(food)
            Navigation.findNavController(bind.root).navigate(nav)
        }

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}