package com.works.foodorderapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.entity.Foods
import com.works.foodorderapplication.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(var frepo: FoodsRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()
    var sourceFoodList = MutableLiveData<List<Foods>>()
    var selectionFoodList = MutableLiveData<List<Foods>>()

    init {
        loadFoods()
    }

    fun loadFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.loadFoods()
            sourceFoodList.value = foodList.value
            selectionFoodList.value = foodList.value
        }
    }

    fun sortList(criteria: String){
        CoroutineScope(Dispatchers.Main).launch {
            when(criteria){
                "nameAsc" -> foodList.value = foodList.value?.sortedBy { it.food_name }
                "nameDesc" -> foodList.value = foodList.value?.sortedByDescending { it.food_name }
                "priceAsc" -> foodList.value = foodList.value?.sortedBy { it.food_price }
                "priceDesc" -> foodList.value = foodList.value?.sortedByDescending { it.food_price }
                else -> loadFoods()
            }
        }
    }

    fun searchList(query: String){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = sourceFoodList.value?.filter { it.food_name.contains(query, true) }
        }
    }

    fun getDaily(): Foods{
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1
        return selectionFoodList.value?.get(day % selectionFoodList.value!!.size)!!
    }

    fun getSelection(): ArrayList<Foods> {
        val selectionList = ArrayList<Foods>()

        val food1 = selectionFoodList.value?.find { it.food_name.contains("köfte", true) }
        val food2 = selectionFoodList.value?.find { it.food_name.contains("ayran", true) }
        val food3 = selectionFoodList.value?.find { it.food_name.contains("sütlaç", true) }

        selectionList.add(food1!!)
        selectionList.add(food2!!)
        selectionList.add(food3!!)
        return selectionList
    }

    fun filterCategories(category: Int){
        CoroutineScope(Dispatchers.Main).launch {
            when(category){
                0 -> foodList.value = sourceFoodList.value
                1 -> foodList.value = sourceFoodList.value?.filter { it.food_name.contains("izgara", true)
                        || it.food_name.contains("köfte", true)
                        || it.food_name.contains("lazanya", true)
                        || it.food_name.contains("makarna", true)
                        || it.food_name.contains("pizza", true) }
                2 -> foodList.value = sourceFoodList.value?.filter { it.food_name.contains("ayran", true)
                        || it.food_name.contains("fanta", true)
                        || it.food_name.contains("kahve", true)
                        || it.food_name.equals("su", true) }
                3 -> foodList.value = sourceFoodList.value?.filter { it.food_name.contains("baklava", true)
                        || it.food_name.contains("kadayıf", true)
                        || it.food_name.contains("sütlaç", true)
                        || it.food_name.contains("tiramisu", true) }
            }
        }
    }
}