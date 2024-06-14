package domain.repository

import data.question.Difficulty
import data.question.Question

interface QuestionRepository {
    fun getRandomQuestions(): List<Question>
    fun getRandomQuestionsByDifficulty(difficulty: Difficulty): List<Question>
}