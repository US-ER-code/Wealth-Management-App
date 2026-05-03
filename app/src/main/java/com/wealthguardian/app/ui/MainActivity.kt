package com.wealthguardian.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wealthguardian.app.R
import com.wealthguardian.app.databinding.ActivityMainBinding
import com.wealthguardian.app.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding

    // Declare HomeViewModel at activity scope so fragments can share it via activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val nav = navHostFragment.navController
        b.bottomNavigation.setupWithNavController(nav)
    }
}
