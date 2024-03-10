package com.shrey.quizzy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.firestore.FirebaseFirestore
import com.shrey.quizzy.R
import com.shrey.quizzy.adapters.QuizAdapter
import com.shrey.quizzy.models.Quiz

class MainActivity : AppCompatActivity() {
    // Late-initialized properties
    lateinit var drawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var fireStore: FirebaseFirestore

    var quizList = mutableListOf<Quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()

    }

    private fun setView() {
        setUpFireStore()
        setDrawerToggle()
        populateData()
        setRecyclerView()

    }
    // Method to set up Firebase Firestore
    private fun setUpFireStore() {
        fireStore = FirebaseFirestore.getInstance() // Get Firestore instance
        val collections = fireStore.collection("user31") // Access "user31" collection
        collections.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error while retrieving data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            //'value' is the QuerySnapshot object obtained from the Firestore database.
            //convert firestore documents into Quize objects
            Log.d("DATAA", value.toObjects(Quiz::class.java).toString()) // Log retrieved data
        }

    }

    // Method to populate data into the quiz list
    private fun populateData() {
        // Add sample quiz data to the list
        quizList.add(Quiz("1", "10/3/2024"))
        quizList.add(Quiz("1", "11/3/2024"))
        quizList.add(Quiz("1", "12/3/2024"))
        quizList.add(Quiz("1", "13/3/2024"))
        quizList.add(Quiz("1", "14/3/2024"))
        quizList.add(Quiz("1", "15/3/2024"))
        quizList.add(Quiz("1", "16/3/2024"))
        quizList.add(Quiz("1", "17/3/2024"))
        quizList.add(Quiz("1", "18/3/2024"))
        quizList.add(Quiz("1", "19/3/2024"))
        quizList.add(Quiz("1", "20/3/2024"))
        quizList.add(Quiz("1", "21/3/2024"))
        quizList.add(Quiz("1", "22/3/2024"))
        quizList.add(Quiz("1", "23/3/2024"))
        quizList.add(Quiz("1", "24/3/2024"))
        quizList.add(Quiz("1", "25/3/2024"))
        quizList.add(Quiz("1", "26/3/2024"))
        quizList.add(Quiz("1", "27/3/2024"))
        quizList.add(Quiz("1", "28/3/2024"))
        quizList.add(Quiz("1", "29/3/2024"))
    }

    // Method to set up the RecyclerView
    private fun setRecyclerView() {
        val recycle: RecyclerView = findViewById(R.id.recyclerViewId)
        adapter = QuizAdapter(this, quizList)
        recycle.layoutManager = GridLayoutManager(this, 2)
        recycle.adapter = adapter

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
