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

class SignupActivity : AppCompatActivity() {

    // Firebase Authentication instance
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Find the Signup Button in the layout
        val button: Button = findViewById(R.id.btnSignup)
        // Set OnClickListener to the Signup Button
        button.setOnClickListener{
            // Call the signUpUser function when the Signup Button is clicked
            signUpUser()
        }
        val textButtonLogin: TextView = findViewById(R.id.txtButtonLogin)
        textButtonLogin.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to sign up a new user
    private fun signUpUser() {
        // Find the email, password, and confirm password EditText fields in the layout
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val etConfirmPass: EditText = findViewById(R.id.etConfirmPass)

        // Get the text entered in the EditText fields
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPass.text.toString()

        // Check if any field is blank
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if password and confirm password match
        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a new user with email and password using Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {

                    // Show a success message if user creation is successful
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()

                    // If the user is successfully signed un, then redirect to MainActivity and end the SignUpActivity Activity
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // Show an error message if user creation fails
                    Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
