package data

data class ResultRequest<T>(
    val name: String,
    val data: T
)
