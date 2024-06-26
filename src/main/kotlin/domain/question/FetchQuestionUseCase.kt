package domain.question

import data.entity.Difficulty
import data.entity.QuestionRequest
import data.entity.QuestionRequestAction
import data.entity.Result

class FetchQuestionUseCase(
    private val getRandomQuestionsUseCase: GetRandomQuestionsUseCase,
    private val getRandomQuestionsByDifficultyUseCase: GetRandomQuestionsByDifficultyUseCase
) {
    suspend operator fun invoke(questionRequest: QuestionRequest): Result.QuestionResult {
        return when (questionRequest.action) {
            QuestionRequestAction.DIFFICULTY -> {
                questionRequest.data?.let {
                    val difficultyValue = Difficulty.valueOf(it)
                    val questionList = getRandomQuestionsByDifficultyUseCase(difficultyValue)
                    Result.QuestionResult(questionList)
                } ?: Result.QuestionResult(emptyList())
            }

            else -> {
                val questionList = getRandomQuestionsUseCase()
                Result.QuestionResult(questionList)
            }
        }
    }
}