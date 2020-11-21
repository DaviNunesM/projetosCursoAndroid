package com.example.intencoes

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_data)



        val print_data = findViewById<TextView>(R.id.print_data)
        val intent = intent;
        val pegar = intent.extras;
    // colocar dentro do TextView dessa tela
        print_data.text = pegar!!["data"].toString()
        }
}