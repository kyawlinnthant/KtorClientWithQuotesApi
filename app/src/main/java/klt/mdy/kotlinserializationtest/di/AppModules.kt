package klt.mdy.kotlinserializationtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.util.network.*
import klt.mdy.kotlinserializationtest.repo.QuotesRepository
import klt.mdy.kotlinserializationtest.repo.QuotesRepositoryImpl
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun providesHttpClient() : HttpClient{
        return try {
            HttpClient(CIO){
                install(JsonFeature){
                    serializer = KotlinxSerializer(Json)
                }
                install(HttpTimeout){
                    requestTimeoutMillis = 2000L
                }
            }
        }catch (e :UnresolvedAddressException ){
            throw UnresolvedAddressException()
        }
    }
    @Provides
    @Singleton
    fun providesQuoteRepository(client : HttpClient) : QuotesRepository {
        return QuotesRepositoryImpl(client = client)
    }
}