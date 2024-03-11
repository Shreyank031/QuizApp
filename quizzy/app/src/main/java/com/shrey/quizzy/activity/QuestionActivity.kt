package com.shrey.quizzy.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.shrey.quizzy.R
import com.shrey.quizzy.adapters.OptionAdapter
import com.shrey.quizzy.databinding.ActivityMainBinding
import com.shrey.quizzy.databinding.ActivityQuestionBinding
import com.shrey.quizzy.models.Question
import com.shrey.quizzy.models.Quiz

class QuestionActivity : AppCompatActivity() {
    lateinit var adapterr: OptionAdapter
       // View binding for the activity
    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
               // Initialize view binding
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        yeahBoi()
    }

    private fun yeahBoi() {
        bindView()
        setUpFireStore()

    }

    private fun setUpFireStore() {
        val fireStore = FirebaseFirestore.getInstance()
               // Query Firestore collection for specific data
        fireStore.collection("user31").whereEqualTo("title", "11-03-2024")
            .get()
            //Query and Cast the data as it's in json format. Handle query success
            .addOnSuccessListener {
                Log.d("loggy", it.toObjects(Quiz::class.java).toString()) // Log retrieved data
            }
    }


    private fun bindView() {
        // Sample question data
        val quest = Question(
            "Whom did Britishers feared the most during Indian Independence struggle?",
            "A. Gandhiji",
            "B. Vivekananda",
            "C. Subhas Chandra Bose",
            "D. Nehru",
            "Subhas Chandra Bose"

        )

        //var description: TextView = findViewById(R.id.txtDescription)
        //var recycle2: RecyclerView = findViewById(R.id.recyclerView2)

        binding.txtDescription.text = quest.description
        adapterr = OptionAdapter(this, quest)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.adapter = adapterr
        binding.recyclerView2.setHasFixedSize(true)


    }

}