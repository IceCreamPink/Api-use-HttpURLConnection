package com.example.testingapi3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.testingapi3.databinding.ActivityAddBinding
import com.example.testingapi3.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = Adapter(mutableListOf())

        val names:EditText = binding.texttitle
        val bodys:EditText = binding.textbody
        val btnkonfir:Button= binding.send

        btnkonfir.setOnClickListener {
            val name =names.text.toString().trim()
            val body =bodys.text.toString().trim()
            if(name.isNotEmpty() && body.isNotEmpty()){
                val newRequest = Request(userId = 1, id = 101, title = name, body = body)
                adapter.adddata(newRequest)

//                untuk menampilkan pesan ketika berhasil ditambahkan
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()

//                melanjutkan ke main(untuk melihat data)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "data belum lengkap", Toast.LENGTH_SHORT).show()
            }
        }

        val add: ImageButton = findViewById(R.id.imageButton)
        add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}