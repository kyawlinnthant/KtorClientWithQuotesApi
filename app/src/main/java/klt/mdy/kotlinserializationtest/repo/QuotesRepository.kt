package klt.mdy.kotlinserializationtest.repo

import klt.mdy.kotlinserializationtest.common.Resource
import klt.mdy.kotlinserializationtest.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    suspend fun fetchNewQuote(): Flow<Resource<Quote>>
}