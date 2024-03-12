package com.shrey.quizzy.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.shrey.quizzy.R
import com.shrey.quizzy.activity.MainActivity
import com.shrey.quizzy.activity.QuestionActivity
import com.shrey.quizzy.models.Question
import com.shrey.quizzy.models.Quiz
import com.shrey.quizzy.utils.ColorPicker
import com.shrey.quizzy.utils.IconPicker

//Adapter class
class QuizAdapter(val context: Context, val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    //Create View Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quize_item, parent, false)
        return QuizViewHolder(view)
    }

    //Get item count
    override fun getItemCount(): Int {
        return quizzes.size
    }

    //Bind data to view holder
    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.txtTitle.text = quizzes[position].title
        holder.cardCont.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.imgIcon.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Title ${holder.txtTitle.text}", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("Date", holder.txtTitle.text)
            context.startActivity(intent)

        }

    }

    //ViewHolder inner class
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val imgIcon: ImageView = itemView.findViewById(R.id.imgQuizIcon)
        val cardCont: CardView = itemView.findViewById(R.id.cardContainer)

    }
}