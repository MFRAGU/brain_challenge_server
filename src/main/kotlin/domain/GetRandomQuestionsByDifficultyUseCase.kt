package domain

import data.question.Difficulty
import domain.repository.QuestionRepository

class GetRandomQuestionsByDifficultyUseCase(
    private val questionRepository: QuestionRepository
) {
    operator fun invoke(difficulty: Difficulty) =
        questionRepository.getRandomQuestionsByDifficulty(difficulty)
}