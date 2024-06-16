package data.entity

sealed class Result(
    val name: RequestType
) {
    data class QuestionResult(val data: List<Question>) : Result(RequestType.QUESTION)
    data class BadResult(val message: String) : Result(RequestType.ERROR)
}
