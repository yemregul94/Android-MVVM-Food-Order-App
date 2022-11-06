package com.works.foodorderapplication.data.repo

import com.google.firebase.auth.FirebaseUser
import com.works.foodorderapplication.data.datasource.UserDataSource

class UserRepository(var uds: UserDataSource) {

    fun getUser(): FirebaseUser? = uds.getUser()

    suspend fun login(email: String, password: String) = uds.login(email, password)

    suspend fun register(email: String, password: String) = uds.register(email, password)

    fun reset(email: String) = uds.reset(email)

    fun signOut() = uds.signOut()
}