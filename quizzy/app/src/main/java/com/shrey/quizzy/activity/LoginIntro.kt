package com.shrey.quizzy.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.shrey.quizzy.R

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

        //Firebase instance
        val firebase = FirebaseAuth.getInstance()
        if (firebase.currentUser != null) {
            intentLogin("MAIN")
        }

        val buttonGetStarted: Button = findViewById(R.id.btnGetStarted)
        buttonGetStarted.setOnClickListener {
            intentLogin("LOGIN")
        }
    }

    private fun intentLogin(name: String) {
        val intent = when (name) {

            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("Unexpected error")

        }
        startActivity(intent)
        finish()
    }


}
