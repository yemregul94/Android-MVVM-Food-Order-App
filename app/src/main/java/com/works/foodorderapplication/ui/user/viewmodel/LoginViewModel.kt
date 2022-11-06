package com.works.foodorderapplication.ui.user.viewmodel

import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var urepo: UserRepository): ViewModel() {

    suspend fun login(email: String, password: String){
        if(email != "" && password != ""){
            urepo.login(email, password)
        }
    }
}