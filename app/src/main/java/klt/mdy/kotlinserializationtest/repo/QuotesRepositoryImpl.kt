package klt.mdy.kotlinserializationtest.repo

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import klt.mdy.kotlinserializationtest.BuildConfig
import klt.mdy.kotlinserializationtest.common.Endpoints
import klt.mdy.kotlinserializationtest.common.Resource
import klt.mdy.kotlinserializationtest.common.safeApiCall
import klt.mdy.kotlinserializationtest.data.RandomQuotesDto
import klt.mdy.kotlinserializationtest.model.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(
    private val client: HttpClient,
) : QuotesRepository {
    override suspend fun fetchNewQuote(): Flow<Resource<Quote>> {
        val request = Endpoints.RANDOM_QUOTES
        return flow {
            emit(Resource.Loading())
            emit(
                safeApiCall(
                    response = client.request<RandomQuotesDto>(request) {
                        method = HttpMethod.Get
                        headers {
                            append("X-RapidAPI-Host", "quotes15.p.rapidapi.com")
                            append("X-RapidAPI-Key", BuildConfig.API_KEY)
                        }
                        timeout {
                            requestTimeoutMillis = 2000L
                        }
                    }.toVo()
                )
            )
        }
    }
}

