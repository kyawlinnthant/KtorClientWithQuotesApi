package klt.mdy.kotlinserializationtest.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.network.*
import klt.mdy.kotlinserializationtest.repo.QuotesRepository
import klt.mdy.kotlinserializationtest.repo.QuotesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient {
        return try {
            HttpClient(CIO) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        kotlinx.serialization.json.Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 10000L
                }
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.v("client", message)
                        }
                    }
                    level = LogLevel.ALL
                }
                install(ResponseObserver) {
                    onResponse { response ->
                        Log.d("HTTP status : ", "${response.status.value}")
                    }
                }
                install(DefaultRequest) {
                    header(
                        key = HttpHeaders.ContentType,
                        value = ContentType.Application.Json
                    )
                }
            }
        } catch (e: UnresolvedAddressException) {
            throw UnresolvedAddressException()
        } catch (e: Exception) {
            throw Exception()
        }
    }

    @Provides
    @Singleton
    fun providesQuoteRepository(client: HttpClient): QuotesRepository {
        return QuotesRepositoryImpl(client = client)
    }
}