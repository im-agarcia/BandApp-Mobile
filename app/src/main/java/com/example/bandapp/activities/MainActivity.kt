package com.example.bandapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.bandapp.R
import com.example.bandapp.databinding.ActivityMainBinding
import com.example.bandapp.fragments.main.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= Firebase.auth


        drawerLayout= findViewById(R.id.drawerLayout)
        val navView: NavigationView= findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Default landing fragment
        replaceFragment(ProfileFragment(),"My Profile")

        //Navigation fragment choice
        navView.setNavigationItemSelectedListener {

            it.isChecked= true

            when(it.itemId){
                R.id.profile -> replaceFragment(ProfileFragment(), it.title.toString())
                R.id.buddies -> replaceFragment(BuddiesFragment(), it.title.toString())
                R.id.booking -> replaceFragment(BookingFragment(), it.title.toString())
                R.id.searchBuddies -> replaceFragment(SearchBuddiesFragment(), it.title.toString())
                R.id.changePassword -> replaceFragment(PasswordModificationFragment(), it.title.toString())
                R.id.deleteUser -> replaceFragment(DeleteAccountFragment(), it.title.toString())
                R.id.exit -> signOut()
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String){
            val fragmentManager= supportFragmentManager
        val fragmentTransaction= fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun signOut(){
        Firebase.auth.signOut()
        val intent= Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()

    }
}