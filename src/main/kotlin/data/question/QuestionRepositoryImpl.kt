package data.question

import data.entity.Difficulty
import data.entity.Question

class QuestionRepositoryImpl(
    private val questionExecutor: QuestionExecutor
): QuestionRepository {
    override suspend fun getRandomQuestions(): List<Question> =
        questionExecutor.getRandomQuestions()

    override suspend fun getRandomQuestionsByDifficulty(difficulty: Difficulty): List<Question> =
        questionExecutor.getRandomQuestionsByDifficulty(difficulty)
}