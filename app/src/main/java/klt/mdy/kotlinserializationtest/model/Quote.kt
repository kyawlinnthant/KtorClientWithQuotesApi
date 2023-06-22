package klt.mdy.kotlinserializationtest.model

data class Quote(
    val id : Long = 0L,
    val author : String = "",
    val category : String = "",
    val text : String = ""
)
