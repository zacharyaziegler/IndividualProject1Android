package com.example.individualproj1android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val btnSignUp = findViewById<Button>(R.id.btn_submit_reg) // finding btn to reference
        val etFirstName = findViewById<EditText>(R.id.et_first) // finding all edit texts to reference
        val etLastName = findViewById<EditText>(R.id.et_last)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password_reg)
        val etDob = findViewById<EditText>(R.id.et_dob)


        btnSignUp.setOnClickListener {// on click listener for sign up button
            val firstName = etFirstName.text.toString() // converting all fields to string to validate
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val dob = etDob.text.toString()
            val sharedPrefs = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && dob.isNotEmpty()) {
                // if fields are not empty, continue
                if (firstName.length in 3..30 && lastName.length in 3 .. 30) {
                    // if first and last name in range, continue
                    if (isValidEmail(email)) {
                        // if email is valid, continue
                        if (password.length >= 5) {
                            // if password length is 5 or >, continue
                            if (isValidDob(dob)) {
                                // if dob is valid dd/mm/yy
                                // all fields are now valid
                                val user = User(firstName, lastName, email, password, dob) // create user object
                                 // shared prefs so user can login in login screen, wasn't sure how to go about this without using firebase
                                val editor = sharedPrefs.edit();
                                editor.putString("email", email)
                                editor.putString("firstName", firstName)
                                editor.putString("lastName", lastName)
                                editor.putString("password", password)
                                editor.putString("dob", dob)
                                editor.apply()
                                val successMsg = getString(R.string.success)
                                Toast.makeText(this, successMsg, Toast.LENGTH_SHORT).show()
                                val i = Intent(this, MainActivity::class.java) // create intent of main and go back to login page
                                startActivity(i)
                                finish()
                            } else {
                                val errorMsg = getString(R.string.dob_error)
                                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                            }
                        } else {
                            // error message
                            val errorMsg = getString(R.string.pass_error)
                            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        // error message
                        val errorMsg = getString(R.string.email_error)
                        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                    }
                } else {
                    // error message
                    val errorMsg = getString(R.string.length_error)
                    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                }
            } else {
                // error message
                val errorMsg = getString(R.string.empty_field_error)
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        }

    }

    fun isValidDob(dob: String): Boolean { // validate DOB using regex
        val dobRegex = "\\d{2}/\\d{2}/\\d{2}"
        return dob.matches(dobRegex.toRegex())
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$" // validate email using regex
        return email.matches(emailRegex.toRegex())
    }
}