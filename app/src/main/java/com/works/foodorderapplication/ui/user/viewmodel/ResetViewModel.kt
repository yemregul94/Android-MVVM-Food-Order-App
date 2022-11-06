package com.works.foodorderapplication.ui.user.viewmodel

import androidx.lifecycle.ViewModel
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetViewModel @Inject constructor(var urepo: UserRepository): ViewModel() {

    fun reset(email: String){
        if(email != ""){
            urepo.reset(email)
        }
    }
}