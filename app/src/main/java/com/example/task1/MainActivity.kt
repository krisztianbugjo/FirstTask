package com.example.task1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main.setOnClickListener() {
            changeActivity()
        }

    }

    private fun changeActivity() {
        val intent = Intent(this, SecondActivity::class.java).apply {}
        startActivity(intent)
    }
}
