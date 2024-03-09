package com.shrey.quizzy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.shrey.quizzy.R

class LoginActivity : AppCompatActivity() {
    // Firebase Authentication instance
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Find the Login Button in the layout
        val btnLogin: Button = findViewById(R.id.btnLogin)
        // Set OnClickListener to the Login Button
        btnLogin.setOnClickListener {
            // Call the loginUser function when the Login Button is clicked
            loginUser()
        }
        val buttonSignUpInLogin: TextView = findViewById(R.id.txtButtonLogin)
        buttonSignUpInLogin.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to log in an existing user
    private fun loginUser() {
        // Find the email and password EditText fields in the layout
        val emailField: EditText = findViewById(R.id.etEmaill)
        val passwordField: EditText = findViewById(R.id.etPasswordd)

        // Get the text entered in the EditText fields
        val email = emailField.text.toString()
        val password = passwordField.text.toString()

        // Check if email or password is blank
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }

        // Sign in the user with email and password using Firebase Authentication
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                // Show a success message if login is successful
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // If the user is successfully logged in, then redirect to MainActivity and end the Login Activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // Show an error message if login fails
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
