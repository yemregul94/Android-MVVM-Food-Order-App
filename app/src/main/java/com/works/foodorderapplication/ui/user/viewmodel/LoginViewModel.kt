package com.works.foodorderapplication.ui.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var urepo: UserRepository): ViewModel() {
    var errorMessage = MutableLiveData<String>()

    suspend fun login(email: String, password: String){
        if(email != "" && password != ""){
            try {
                urepo.login(email, password)
            }catch (e: Exception){
                errorMessage.value = e.localizedMessage?.toString()
            }
        }else{
            errorMessage.value = "Email ve Şifre boş bırakılamaz"
        }
    }
}