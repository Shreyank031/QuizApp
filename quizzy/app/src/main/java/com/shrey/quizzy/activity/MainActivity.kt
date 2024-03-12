package com.shrey.quizzy.activity

import android.content.Context
import android.content.Intent
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
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.shrey.quizzy.R
import com.shrey.quizzy.adapters.QuizAdapter
import com.shrey.quizzy.databinding.ActivityMainBinding
import com.shrey.quizzy.models.Question
import com.shrey.quizzy.models.Quiz
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    // Late-initialized properties
    lateinit var drawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var fireStore: FirebaseFirestore
    private lateinit var binding: ActivityMainBinding
    var quizList = mutableListOf<Quiz>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()

    }

    private fun setView() {
        setUpFireStore()
        setDrawerToggle()
        setRecyclerView()
        setDatePicker()

    }

    private fun setDatePicker() {
        binding.btnDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "Huyoo")

            //Events to handle like 'ok' 'cancel' and clicking back button
            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                Log.d("Date", date)
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("Date", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEE", datePicker.headerText)

            }

            datePicker.addOnCancelListener {
                Log.d("DATEE", "User pressed back button")
            }
        }
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
            //convert firestore documents into Quiz objects
            Log.d("DATAA", value.toObjects(Quiz::class.java).toString()) // Log retrieved data


            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()

        }

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
