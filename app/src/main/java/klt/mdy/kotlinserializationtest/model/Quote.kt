package klt.mdy.kotlinserializationtest.model

data class Quote(
    val id: Long = -1L,
    val data: String = "",
    val writer: String = "",
    val tags: List<String> = listOf(),
)
