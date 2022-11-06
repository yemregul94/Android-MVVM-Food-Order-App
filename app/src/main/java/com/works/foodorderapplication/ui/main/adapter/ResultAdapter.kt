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
import com.works.foodorderapplication.databinding.RowResultBinding
import com.works.foodorderapplication.ui.main.view.FoodCartFragmentDirections
import com.works.foodorderapplication.ui.main.view.FoodListFragmentDirections
import com.works.foodorderapplication.ui.main.viewmodel.FoodCartViewModel
import com.works.foodorderapplication.ui.main.viewmodel.ResultViewModel
import com.works.foodorderapplication.utils.BASE_URL

class ResultAdapter(var mContext: Context,
                    var cartList: List<Cart>,
                    var viewModel: ResultViewModel) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(binding: RowResultBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: RowResultBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowResultBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_result, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cart = cartList.get(position)
        val bind = holder.binding
        bind.orderObject = cart

        val url = "$BASE_URL/yemekler/resimler/${cart.food_image_name}"
        Glide.with(bind.ivOrderImage).load(url).override(50, 50).into(bind.ivOrderImage)


    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}