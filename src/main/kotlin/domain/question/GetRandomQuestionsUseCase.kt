package domain.question

import data.question.QuestionRepository

class GetRandomQuestionsUseCase(private val questionRepository: QuestionRepository) {
    suspend operator fun invoke() = questionRepository.getRandomQuestions()
}