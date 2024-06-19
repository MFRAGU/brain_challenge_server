package domain.question

import data.entity.Difficulty
import data.question.QuestionRepository

class GetRandomQuestionsByDifficultyUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend operator fun invoke(difficulty: Difficulty) =
        questionRepository.getRandomQuestionsByDifficulty(difficulty)
}