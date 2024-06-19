package data.entity

data class Question(
    val category: String,
    val id: String,
    val correctAnswer: String,
    val incorrectAnswers: String,
    val question: String,
    val difficulty: String
)
