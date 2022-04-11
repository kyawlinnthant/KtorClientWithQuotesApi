package klt.mdy.kotlinserializationtest.common

import io.ktor.client.features.*
import io.ktor.network.sockets.*
import java.io.IOException

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : Resource<T>()
    class Error<T>(error: String) : Resource<T>(null, error)
    class Success<T>(data: T) : Resource<T>(data)

}

fun <T> safeApiCall(
    response: T,
): Resource<T> {
    return try {
        Resource.Success(data = response)
    } catch (e: Exception) {
        Resource.Error(e.message ?: e.toString())
    } catch (e: IOException) {
        Resource.Error(e.message ?: e.toString())
    } catch (e : HttpRequestTimeoutException){
        Resource.Error(e.message ?: e.toString())
    }catch (e : ConnectTimeoutException){
        Resource.Error(e.message ?: e.toString())
    }catch (e : SocketTimeoutException){
        Resource.Error(e.message ?: e.toString())
    }
}