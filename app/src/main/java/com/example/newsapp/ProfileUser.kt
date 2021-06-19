package com.example.newsapp

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileUser : AppCompatActivity() {
    private lateinit var nav_host: NavController
    private lateinit var bottonNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        nav_host = findNavController(R.id.fragmentContainerView)

        bottonNav = findViewById(R.id.navigation_view)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.first_Fragmets,
                R.id.recentFragment,
                R.id.logout
            )
        )

        setupActionBarWithNavController(nav_host, appBarConfiguration)
        bottonNav.setupWithNavController(nav_host)
        bottonNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_homeLink -> {
                    nav_host.navigate(R.id.first_Fragmets)
                }
                R.id.action_archiveLink -> {
                    nav_host.navigate(R.id.recentFragment)
                }
                R.id.action_userLink -> {
                    nav_host.navigate(R.id.logout)
                }
            }
            true
        }


    }


}