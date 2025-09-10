package com.example.khetimitra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load HomeFragment by default
        replaceFragment(HomeFragment())

        // Bottom Navigation setup
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_community -> replaceFragment(CommunityFragment())
                R.id.nav_market -> replaceFragment(MarketFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
            }
            true
        }

        // ✅ Safe EditText color setting for any fragment
        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentViewCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    v: android.view.View,
                    savedInstanceState: Bundle?
                ) {
                    super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                    try {
                        // Call the view-based function, NOT the fragment one
                        (application as KhetiMitraApp).setEditTextColors(v)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }, true
        )
    }

    // Safe function to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment?.javaClass != fragment.javaClass) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commitAllowingStateLoss() // safer for lifecycle crashes
        }
    }
}
