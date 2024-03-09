package com.shrey.quizzy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.shrey.quizzy.R

class MainActivity : AppCompatActivity() {
    lateinit var drawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

    }

    private fun setView() {
        setDrawerToggle()
    }

    // Method to set up the navigation drawer toggle
    private fun setDrawerToggle() {
        val toolBar: MaterialToolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolBar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)

        // Initialize the ActionBarDrawerToggle
        drawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        drawerToggle.syncState()
    }

    // Handle options menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
