package data.question

import domain.repository.QuestionRepository

internal class QuestionRepositoryImpl(
    private val questionExecutor: QuestionExecutor
): QuestionRepository {
    override fun getRandomQuestions(): List<Question> =
        questionExecutor.getRandomQuestions()

    override fun getRandomQuestionsByDifficulty(difficulty: Difficulty): List<Question> =
        questionExecutor.getRandomQuestionsByDifficulty(difficulty)
}