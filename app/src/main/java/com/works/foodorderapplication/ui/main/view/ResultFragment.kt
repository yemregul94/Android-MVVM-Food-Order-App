package com.works.foodorderapplication.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.works.foodorderapplication.R
import com.works.foodorderapplication.data.entity.Cart
import com.works.foodorderapplication.databinding.FragmentResultBinding
import com.works.foodorderapplication.ui.main.MainViewModel
import com.works.foodorderapplication.ui.main.adapter.FoodCartAdapter
import com.works.foodorderapplication.ui.main.adapter.ResultAdapter
import com.works.foodorderapplication.ui.main.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var sharedViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_result, container, false)
        binding.resultFragment = this

        sharedViewModel.orderList.observe(viewLifecycleOwner){
            if(it != null){
                val adapter = ResultAdapter(requireContext(), it, viewModel)
                binding.resultAdapter = adapter
            }
        }

        Glide.with(binding.root).load(R.drawable.splash_gif).override(500, 500).into(binding.imageViewOrder)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: ResultViewModel by viewModels()
        viewModel = tempViewModel
        val tempSharedViewModel: MainViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }



}