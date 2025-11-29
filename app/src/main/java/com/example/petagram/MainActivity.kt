package com.example.petagram

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.petagram.databinding.ActivityMainBinding
import com.example.petagram.ui.about.AboutActivity
import com.example.petagram.ui.contact.ContactActivity
import com.example.petagram.ui.home.HomeFragment
import com.example.petagram.ui.profile.ProfileFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupViewPager()
    }

    private fun setupViewPager() {
        val fragments = listOf(
            HomeFragment(),     // Lista de mascotas
            ProfileFragment()   // Perfil con grid de fotos
        )
        val adapter = object : androidx.viewpager2.adapter.FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size
            override fun createFragment(position: Int) = fragments[position]
        }
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Inicio"
                1 -> "Perfil"
                else -> "Tab ${position + 1}"
            }
        }.attach()
    }

    // ✅ Inflar el menú
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // ✅ Manejar clics del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_contact -> {
                startActivity(Intent(this, ContactActivity::class.java))
                true
            }
            R.id.action_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

