package klt.mdy.kotlinserializationtest.data

import klt.mdy.kotlinserializationtest.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class RandomQuoteDto(
    val id : Long,
    val text : String,
    val author : String,
    val category : String
) {
    fun toVo(): Quote {
        return Quote(
            id = id,
            author = author,
            category = category,
            text =  text
        )
    }
}

@Serializable
data class Originator(
    val id: Int,
    val name: String,
    val url: String
)

/*
@Serializable
data class Article(
    val author: String,
    val content: String,
    val description: String,
    @Transient
    val publishedAt: String = "",
    val source: Source,
    val title: String,
    @SerialName(value = "url")
    val websiteUrl: String,
    @SerialName(value = "urlToImage")
    val imageUrl: String
)*/
