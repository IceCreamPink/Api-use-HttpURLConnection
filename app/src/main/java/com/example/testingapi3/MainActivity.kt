package com.example.testingapi3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingapi3.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reyview.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(mutableListOf())
        binding.reyview.adapter = adapter


        val adapter = Adapter(mutableListOf())
        binding.reyview.adapter = adapter

//      untuk menampilkan data
        adapter.livedata()

//        untuk mengirim id dan value

/*

// Contoh memperbarui data pada posisi tertentu
        val updatedRequest = Request(userId = 1, id = 101, title = "Updated Post", body = "This is an updated post.")
        adapter.updateRequest(0, updatedRequest)
*/


        val add:Button = findViewById(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


    }
}