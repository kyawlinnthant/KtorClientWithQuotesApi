package klt.mdy.kotlinserializationtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
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
        return HttpClient(CIO){
            install(JsonFeature){
                serializer = KotlinxSerializer(Json)
            }
        }
    }
    @Provides
    @Singleton
    fun providesQuoteRepository(client : HttpClient) : QuotesRepository {
        return QuotesRepositoryImpl(client = client)
    }
}