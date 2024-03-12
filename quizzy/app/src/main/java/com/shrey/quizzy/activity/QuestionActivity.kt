package com.shrey.quizzy.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.shrey.quizzy.adapters.OptionAdapter
import com.shrey.quizzy.databinding.ActivityQuestionBinding
import com.shrey.quizzy.models.Question
import com.shrey.quizzy.models.Quiz

class QuestionActivity : AppCompatActivity() {
    private lateinit var adapter: OptionAdapter

    private var quizzes: MutableList<Quiz>? = null
    private var questions: MutableMap<String, Question>? = null
    private var index = 1

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
        setUpFireStore()
        setUpEventListeners()

    }

    private fun setUpEventListeners() {

        binding.btnNext.setOnClickListener {
            index++
            bindView()
        }

        binding.btnTrue.setOnClickListener {
            index--
            bindView()
        }

        binding.btnSubmit.setOnClickListener {
            Log.d("FINAL_QUIZ", questions.toString())
        }

    }

    private fun setUpFireStore() {
        val fireStore = FirebaseFirestore.getInstance()
        val date = intent.getStringExtra("Date")
        if (date != null) {
            // Query FireStore collection for specific data
            fireStore.collection("user31").whereEqualTo("title", date).get()
                //Query and Cast the data as it's in json format. Handle query success
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        Log.d(
                            "log_y", it.toObjects(Quiz::class.java).toString()
                        )
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindView()

                    }
                }
        }
    }


    private fun bindView() {

        binding.btnTrue.visibility = View.GONE
        binding.btnNext.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        when (index) {
            1 -> {
                binding.btnNext.visibility = View.VISIBLE
            }

            questions!!.size -> {
                binding.btnSubmit.visibility = View.VISIBLE
                binding.btnTrue.visibility = View.VISIBLE
            }

            else -> {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnTrue.visibility = View.VISIBLE
            }
        }
        val quest = questions!!["question$index"]


        binding.txtDescription.text = quest!!.description
        adapter = OptionAdapter(this, quest)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this)
        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.setHasFixedSize(true)


    }

}