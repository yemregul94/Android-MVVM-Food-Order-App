package com.works.foodorderapplication.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.works.foodorderapplication.R
import com.works.foodorderapplication.data.entity.Foods
import com.works.foodorderapplication.databinding.RowFoodsBinding
import com.works.foodorderapplication.ui.main.view.FoodListFragmentDirections
import com.works.foodorderapplication.ui.main.viewmodel.FoodListViewModel
import com.works.foodorderapplication.utils.BASE_URL

class FoodListAdapter(var mContext: Context,
                      var foodList: List<Foods>,
                      var viewModel: FoodListViewModel) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: RowFoodsBinding) : RecyclerView.ViewHolder(binding.root){
        var binding: RowFoodsBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowFoodsBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.row_foods, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foodList.get(position)
        val bind = holder.binding
        bind.foodObject = food

        val url = "$BASE_URL/yemekler/resimler/${food.food_image_name}"
        Glide.with(bind.ivListImage).load(url).override(300, 300).into(bind.ivListImage)

        bind.RowCard.setOnClickListener {
            val nav = FoodListFragmentDirections.listToDetails(food)
            Navigation.findNavController(it).navigate(nav)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}