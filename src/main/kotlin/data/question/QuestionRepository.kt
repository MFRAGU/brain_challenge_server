package data.question

import data.entity.Difficulty
import data.entity.Question

interface QuestionRepository {
    suspend fun getRandomQuestions(): List<Question>
    suspend fun getRandomQuestionsByDifficulty(difficulty: Difficulty): List<Question>
}