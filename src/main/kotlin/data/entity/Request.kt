package data.entity

data class Request(
    val type: String,
    val action: String,
    val data: String
)

enum class RequestType {
    QUESTION,
    ERROR
}