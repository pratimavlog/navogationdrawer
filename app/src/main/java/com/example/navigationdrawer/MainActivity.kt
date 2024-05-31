package com.example.navigationdrawer

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawr_lyout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        drawr_lyout= findViewById(R.id.drawr_lyout)

        var toolbar:Toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var navigation_view:NavigationView=findViewById(R.id.navigation_view)
        navigation_view.setNavigationItemSelectedListener(this)

        val toggle=ActionBarDrawerToggle(this,drawr_lyout,toolbar,R.string.open_nav,R.string.close_nav)
        drawr_lyout.addDrawerListener(toggle)
        toggle.syncState()


        if(savedInstanceState==null){
            replacefragment(HomeFragment())
            navigation_view.setCheckedItem(R.id.nav_home)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.nav_home -> replacefragment(HomeFragment())
            R.id.nav_share -> replacefragment(ShareFragment())
            R.id.nav_setting -> replacefragment(SettingFragment())
            R.id.nav_about -> replacefragment(AboutFragment())
            R.id.nav_logout -> replacefragment(logoutFragment())
        }
        drawr_lyout.closeDrawer(GravityCompat.START)
        return true

    }
    fun replacefragment(fragment: Fragment)
    {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if(drawr_lyout.isDrawerOpen(GravityCompat.START))
        {
            drawr_lyout.closeDrawer(GravityCompat.START)
        }
        else{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}