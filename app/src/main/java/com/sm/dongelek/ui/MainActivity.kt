package com.sm.dongelek.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.sm.dongelek.R
import com.sm.dongelek.databinding.MainActivityBinding
import com.sm.dongelek.utils.BindingActivity
import com.sm.dongelek.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<MainActivityBinding>(MainActivityBinding::inflate) {

    private var currentNavController: LiveData<NavController>? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding.run {
            println("hello")

            val navGraphIds = listOf(
                R.id.nav_home,
                R.id.nav_profile
            )

            currentNavController = bnv.setupWithNavController(
                navGraphIds,
                supportFragmentManager,
                R.id.nav_host_fragment,
                intent
            )
        }
    }

    override fun onBackPressed() {
        if (currentNavController?.value?.navigateUp() != true) {
            super.onBackPressed()
        }
    }
}