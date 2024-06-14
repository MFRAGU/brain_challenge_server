package data

data class Request(
    val action: String,
    val type: String,
    val data: String? = null
)