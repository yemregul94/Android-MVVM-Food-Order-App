package com.works.foodorderapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.works.foodorderapplication.R
import com.works.foodorderapplication.databinding.ActivityMainBinding
import com.works.foodorderapplication.databinding.ActivityUserBinding
import com.works.foodorderapplication.ui.user.UserActivity
import com.works.foodorderapplication.ui.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel

        viewModel.currentUser.observe(this){
            if(it == null){
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}