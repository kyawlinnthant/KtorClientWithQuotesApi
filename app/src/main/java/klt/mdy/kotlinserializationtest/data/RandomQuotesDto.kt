package klt.mdy.kotlinserializationtest.data

import klt.mdy.kotlinserializationtest.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class RandomQuotesDto(
    val id: Long,
    val language_code: String,
    val originator: Originator,
    val content: String,
    val tags: List<String>,
    val url: String,
) {
    fun toVo(): Quote {
        return Quote(
            id = id,
            data = content,
            writer = originator.name,
            tags = tags,
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
