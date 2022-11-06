package com.works.foodorderapplication.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.works.foodorderapplication.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var urepo: UserRepository): ViewModel() {
    var currentUser = MutableLiveData<FirebaseUser>()

    init {
        getUser()
    }

    fun getUser(){
        currentUser.value = urepo.getUser()
    }

}