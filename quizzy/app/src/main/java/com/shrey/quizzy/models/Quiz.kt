package com.shrey.quizzy.models

data class Quiz(
    val id: String = "",
    val title: String = "",
    val questions: MutableMap<String, Question> = mutableMapOf()

)
