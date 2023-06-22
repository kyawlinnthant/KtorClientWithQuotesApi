package klt.mdy.kotlinserializationtest.common

object Endpoints {
    private const val HOST = "https://famous-quotes4.p.rapidapi.com"
    private const val PATH = "/random?category=all&count=1"
    private const val RANDOM = "/random/"

    const val RANDOM_QUOTES = HOST + PATH
}