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
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.FragmentLoginBinding
import com.works.foodorderapplication.ui.user.UserViewModel
import com.works.foodorderapplication.ui.user.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        binding.loginFragment = this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel
        val tempSharedViewModel: UserViewModel by activityViewModels()
        sharedViewModel = tempSharedViewModel
    }

    fun loginButton(email:String, password:String){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.login(email, password)
            sharedViewModel.getUser()
        }
    }

    fun goToRegisterButton(){
        val nav = LoginFragmentDirections.loginToRegister()
        Navigation.findNavController(requireView()).navigate(nav)
    }

    fun goToReset(){
        val nav = LoginFragmentDirections.loginToReset()
        Navigation.findNavController(requireView()).navigate(nav)
    }
}