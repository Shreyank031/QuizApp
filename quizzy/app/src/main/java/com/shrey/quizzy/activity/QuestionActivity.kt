package com.shrey.quizzy.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shrey.quizzy.R
import com.shrey.quizzy.adapters.OptionAdapter
import com.shrey.quizzy.models.Question

class QuestionActivity : AppCompatActivity() {
    lateinit var adapterr: OptionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        bindView()
    }

    private fun bindView() {
        val quest = Question(
            "Whom did Britishers feared the most during Indian Independence struggle?",
            "A. Gandhiji",
            "B. Vivekananda",
            "C. Subhas Chandra Bose",
            "D. Nehru",
            "Subhas Chandra Bose"

        )

        var description: TextView = findViewById(R.id.txtDescription)
        var recycle2: RecyclerView = findViewById(R.id.recyclerView2)
        description.text = quest.description
        adapterr = OptionAdapter(this, quest)
        recycle2.layoutManager = LinearLayoutManager(this)
        recycle2.adapter = adapterr
        recycle2.setHasFixedSize(true)


    }

}