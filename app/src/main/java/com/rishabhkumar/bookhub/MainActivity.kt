package com.rishabhkumar.bookhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout : CoordinatorLayout
    lateinit var toolbar : Toolbar
    lateinit var frameLayout : FrameLayout
    lateinit var navigationView : NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation_view)

        setUpToolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()



        navigationView.setNavigationItemSelectedListener{

            when (it.itemId){
                R.id.dashboard ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on Dashboard",
//                        Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,DashboardFragment())
                        .commit()

                    drawerLayout.close()

                }
                R.id.favourites ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on favourites",
//                        Toast.LENGTH_SHORT).show()
                }
                R.id.profile ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on profile",
//                        Toast.LENGTH_SHORT).show()
                }
                R.id.about ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on about",
//                        Toast.LENGTH_SHORT).show()
                }
            }

            return@setNavigationItemSelectedListener true

        }



    }


    //function for setting up the tool bar
    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    //function for setting up the menu title in left side
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }



}