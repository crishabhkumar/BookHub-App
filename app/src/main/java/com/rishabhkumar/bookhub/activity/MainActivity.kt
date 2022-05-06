package com.rishabhkumar.bookhub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.rishabhkumar.bookhub.fragment.ProfileFragment
import com.rishabhkumar.bookhub.R
import com.rishabhkumar.bookhub.fragment.AboutFragment
import com.rishabhkumar.bookhub.fragment.DashboardFragment
import com.rishabhkumar.bookhub.fragment.FavouritesFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout : CoordinatorLayout
    lateinit var toolbar : Toolbar
    lateinit var frameLayout : FrameLayout
    lateinit var navigationView : NavigationView

    var previousMenuItem :MenuItem ?=  null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation_view)

//        try{
//            setUpToolbar()
//        }catch (e:Exception){
//            println("yahi error h.")
//        }
        setUpToolbar()

//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame,DashboardFragment())
//            .addToBackStack("Dashboard")
//            .commit()
//
//        supportActionBar?.title = "Dashboard"

        //created the function for upper codes
        openDashboard()


        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()



        navigationView.setNavigationItemSelectedListener{


            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it


            when (it.itemId){
                R.id.dashboard ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on Dashboard",
//                        Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, DashboardFragment())
//                        .addToBackStack("Dashboard")
                        .commit()


                    supportActionBar?.title = "Dashboard"

                    drawerLayout.close()

                }
                R.id.favourites ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on favourites",
//                        Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FavouritesFragment())
//                        .addToBackStack("Favourites")
                        .commit()

                    supportActionBar?.title = "Favourites"
                    drawerLayout.close()

                }
                R.id.profile ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on profile",
//                        Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
//                        .addToBackStack("Profile")
                        .commit()

                    supportActionBar?.title = "Profile"
                    drawerLayout.close()
                }
                R.id.about ->{
//                    Toast.makeText(this@MainActivity,
//                        "Clicked on about",
//                        Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
//                        .addToBackStack("About")
                        .commit()


                    supportActionBar?.title = "About App"

                    drawerLayout.close()
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


    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()

        navigationView.setCheckedItem(R.id.dashboard)

        supportActionBar?.title = "Dashboard"
    }


    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)

        when(frag){
            !is DashboardFragment -> openDashboard()

            else -> super.onBackPressed( )

        }

    }



}