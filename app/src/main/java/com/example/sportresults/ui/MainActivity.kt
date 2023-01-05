package com.example.sportresults.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sportresults.R
import com.example.sportresults.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavigationView = mainBinding.bottomNavigation
        bottomNavigationView.setupWithNavController(navController)

        // hides bottom navigation when displaying the list
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.nav_results_list) {
                bottomNavigationView.visibility = View.GONE
            } else {

                bottomNavigationView.visibility = View.VISIBLE
            }
        }

        // Setting the toolbar
        setSupportActionBar(mainBinding.appBarMain.toolbar)
    }
}