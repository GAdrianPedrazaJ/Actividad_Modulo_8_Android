// ui/about/AboutActivity.kt
package com.example.petagram.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petagram.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
