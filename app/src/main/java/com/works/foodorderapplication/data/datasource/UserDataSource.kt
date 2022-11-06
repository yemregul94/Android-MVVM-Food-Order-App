package com.works.foodorderapplication.data.datasource

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataSource @Inject constructor() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun getUser() : FirebaseUser? {
        return auth.currentUser
    }

    suspend fun login(email:String, password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getUser()
            }
        }.addOnFailureListener { exception ->
            
        }.await()
    }

    suspend fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                getUser()
            }
        }.addOnFailureListener { exception ->

        }.await()
    }

    fun reset(email: String){
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful){

            }
        }.addOnFailureListener { exception ->


        }
    }

    fun signOut(){
        auth.signOut()
    }
}