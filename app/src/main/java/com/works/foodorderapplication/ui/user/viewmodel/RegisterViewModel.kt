package com.works.foodorderapplication.ui.user.viewmodel

import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var urepo: UserRepository): ViewModel() {

    suspend fun register(email: String, password: String){
        if(email != "" && password != ""){
            urepo.register(email, password)
        }
    }
}