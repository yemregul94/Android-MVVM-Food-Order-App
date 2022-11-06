package com.works.foodorderapplication.ui.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentResetBinding
import com.works.foodorderapplication.ui.user.viewmodel.ResetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetFragment : Fragment() {

    private lateinit var viewModel: ResetViewModel
    private lateinit var binding: FragmentResetBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_reset, container, false)
        binding.resetFragment = this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ResetViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun resetButton(email:String){
        viewModel.reset(email)
    }

}