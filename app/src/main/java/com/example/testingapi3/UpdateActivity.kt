package com.example.testingapi3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testingapi3.databinding.ActivityMainBinding
import com.example.testingapi3.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}