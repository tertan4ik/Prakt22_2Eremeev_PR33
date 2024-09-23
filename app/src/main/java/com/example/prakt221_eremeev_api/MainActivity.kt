package com.example.prakt221_eremeev_api

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun button1(view: View) {
        editTextLogin = findViewById(R.id.logintext)
        editTextPassword = findViewById(R.id.passwordtext)
        val login = editTextLogin.text.toString()
        val password = editTextPassword.text.toString()

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите логин и пароль", Toast.LENGTH_SHORT).show()
            return
        }
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedLogin = sharedPreferences.getString("login", "ects1234")
        val savedPassword = sharedPreferences.getString("password", "1234")
            // Повторный вход, проверяем данные
            if (login == savedLogin && password == savedPassword) {
                // Данные совпадают, переход на другой экран
                val intent = Intent(this, search_books::class.java)
                startActivity(intent)
            } else {
                // Данные не совпадают, выводим сообщение
                Toast.makeText(this, "Неверный логин или пароль $savedLogin,$savedPassword", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
