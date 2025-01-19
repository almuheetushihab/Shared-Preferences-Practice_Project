package com.example.sharedpreferencespracticeproject

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var sharedPreferences: SharedPreferencesDemo


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
        sharedPreferences = SharedPreferencesDemo(this)
        val (nameValue, ageValue) = sharedPreferences.getData()
        Toast.makeText(this, "get data : $nameValue", Toast.LENGTH_SHORT)
            .show()
        name.setText(nameValue)
        age.setText(ageValue.toString())
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences.saveData(name.text.toString(), age.text.toString().toInt())
        Toast.makeText(
            this,
            "save data : ${name.text}",
            Toast.LENGTH_SHORT
        ).show()
    }
}

class SharedPreferencesDemo(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("MySharedPref", MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun saveData(name: String, age: Int) {
        editor.putString("name", name)
        editor.putInt("age", age)
        editor.apply()
    }

    fun getData(): Pair<String?, Int> {
        val name = sharedPref.getString("name", "")
        val age = sharedPref.getInt("age", 0)
        return Pair(name, age)
    }

}