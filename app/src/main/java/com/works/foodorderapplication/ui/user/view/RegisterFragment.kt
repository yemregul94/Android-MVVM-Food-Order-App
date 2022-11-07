package com.works.foodorderapplication.ui.user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentRegisterBinding
import com.works.foodorderapplication.ui.user.UserViewModel
import com.works.foodorderapplication.ui.user.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register, container, false)
        binding.registerFragment = this

        viewModel.errorMessage.observe(viewLifecycleOwner){
            if(it != null){
                Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RegisterViewModel by viewModels()
        viewModel = tempViewModel
        val tempSharedViewModel: UserViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }

    fun registerButton(email:String, password:String){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.register(email, password)
            sharedViewModel.getUser()
        }
    }

    fun goToReset(){
        val nav = RegisterFragmentDirections.registerToReset()
        Navigation.findNavController(requireView()).navigate(nav)
    }

}