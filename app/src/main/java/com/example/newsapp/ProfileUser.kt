package com.example.newsapp

import android.os.Bundle
import android.view.Menu
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

//        setupActionBarWithNavController(nav_host, appBarConfiguration)
//        setupActionBarWithNavController(nav_host, appBarConfiguration)
        bottonNav.setupWithNavController(nav_host)
        bottonNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_homeLink -> {
                    nav_host.navigate(R.id.first_Fragmets)
                }
                R.id.action_search -> {
                    nav_host.navigate(R.id.recentFragment)
                }
                R.id.action_userLink -> {
                    nav_host.navigate(R.id.logout)
                }
            }
            true
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)

        return true
    }


}