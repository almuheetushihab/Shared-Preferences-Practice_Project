package com.example.sharedpreferencespracticeproject

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.first_editText)
        age = findViewById(R.id.second_editText)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val nameValue = sharedPref.getString("name", "")
        val ageValue = sharedPref.getInt("age", 0)

        name.setText(nameValue)
        age.setText(ageValue.toString())
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        myEdit.putString("name", name.text.toString())
        myEdit.putInt("age", age.text.toString().toInt())
        myEdit.apply()


    }
}