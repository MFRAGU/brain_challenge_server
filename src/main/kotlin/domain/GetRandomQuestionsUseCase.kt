package domain

import domain.repository.QuestionRepository

class GetRandomQuestionsUseCase(private val questionRepository: QuestionRepository) {
    operator fun invoke() = questionRepository.getRandomQuestions()
}