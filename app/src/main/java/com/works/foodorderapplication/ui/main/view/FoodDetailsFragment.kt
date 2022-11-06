package com.works.foodorderapplication.ui.main.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentFoodDetailsBinding
import com.works.foodorderapplication.ui.main.MainViewModel
import com.works.foodorderapplication.ui.main.viewmodel.FoodDetailsViewModel
import com.works.foodorderapplication.utils.BASE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailsBinding
    private lateinit var viewModel: FoodDetailsViewModel
    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_details, container, false)
        binding.foodDetailsFragment = this
        binding.detailToolbarTitle = getString(R.string.detailsToolbar)

        viewModel.userName = sharedViewModel.userName

        viewModel.cartList.observe(viewLifecycleOwner){
            if(it != null){
                sharedViewModel.updateCart(viewModel.incomingCart)
            }
        }

        loadDetails()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailsViewModel by viewModels()
        viewModel = tempViewModel
        val tempSharedViewModel: MainViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }

    fun loadDetails(){
        val bundle: FoodDetailsFragmentArgs by navArgs()
        val foodItem = bundle.food
        binding.foodObject = foodItem

        binding.quantity = "1"
        binding.total = foodItem.food_price.toString()

        val url = "$BASE_URL/yemekler/resimler/${foodItem.food_image_name}"
        Glide.with(binding.root).load(url).override(800, 800).into(binding.ivDetailsImage)

    }

    fun buttonAddToCart(foodName: String, foodImageName: String, foodPrice: Int, foodQuantity: Int) {
        viewModel.addToCart(foodName, foodImageName, foodPrice, foodQuantity, sharedViewModel.userName.value.toString())

        val builder = AlertDialog.Builder(requireView().context)
        builder.setTitle("İşlem Başarılı")
        builder.setMessage("${foodQuantity} adet ${foodName} sepete eklendi")

        builder.setPositiveButton("Sepete Git") { dialog, id ->
            val nav = FoodDetailsFragmentDirections.detailsToCart()
            Navigation.findNavController(requireView()).navigate(nav)
        }
        builder.setNegativeButton("Kapat") { dialog, id ->
        }
        builder.setNeutralButton("Listeye Dön") { dialog, id ->
            Navigation.findNavController(requireView()).popBackStack()
        }
        builder.show()
    }


    fun buttonIncrease(quantity: String){
        binding.quantity = viewModel.buttonIncrease(quantity).toString()
    }

    fun buttonDecrease(quantity: String){
        binding.quantity = viewModel.buttonDecrease(quantity).toString()
    }

    fun checkQuantity(quantity: String, price: Int){
        binding.quantity = viewModel.checkQuantity(quantity)
        binding.total = viewModel.updateTotal(quantity, price).toString()
    }

}