package com.shrey.quizzy.utils

object ColorPicker {
    val colors = arrayOf(
        "#FF5733",
        "#A569BD",
        "#3498DB",
        "#E74C3C",
        "#2ECC71",
        "#F1C40F",
        "#8E44AD",
        "#1ABC9C",
        "#E67E22",
        "#16A085"
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex+1) % colors.size
        return colors[currentColorIndex]
    }
}