package data.entity

data class QuestionRequest(
    val action: QuestionRequestAction,
    val data: String?
) {
    val type = RequestType.QUESTION
}

enum class QuestionRequestAction {
    RANDOM,
    DIFFICULTY
}