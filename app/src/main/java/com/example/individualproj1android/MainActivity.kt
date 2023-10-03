package com.example.individualproj1android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignUp = findViewById<Button>(R.id.btn_sign_up)
        val btnLogIn = findViewById<Button>(R.id.btn_log_in)
        val etEmail = findViewById<EditText>(R.id.et_username)
        val etPassword = findViewById<EditText>(R.id.et_password)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")

        btnLogIn.setOnClickListener{
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (savedEmail == email && savedPassword == password) {
                val i = Intent(this, SuccessfullyLoginActivity::class.java)
                startActivity(i)
                finish()
            } else {
                val errorMsg = getString(R.string.login_error)
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        }

        btnSignUp.setOnClickListener{
            val i = Intent(this, RegistrationActivity::class.java) // create intent of registration and start it on sign up click
            startActivity(i)
            finish()
        }
    }
}