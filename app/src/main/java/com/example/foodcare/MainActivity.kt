package com.example.foodcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.example.myapp.BuyFragment
import com.example.myapp.MapFragment
import com.example.myapp.ProfileFragment
import com.example.myapp.SellFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

@GlideModule
class AppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {

    private val onNavLis: BottomNavigationView.OnNavigationItemSelectedListener
        get() = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottomNavigationBuyId ->{
                    replaceFragment(BuyFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomNavigationSellId->{
                    replaceFragment(SellFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomNavigationMapId->{
                    replaceFragment(MapFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottomNavigationProfileId->{
                    replaceFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

     override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationBar.setOnNavigationItemSelectedListener(onNavLis)
        replaceFragment(BuyFragment())
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_container,fragment)
        fragmentTransaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
