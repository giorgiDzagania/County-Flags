package com.example.flagsapp.core

import retrofit2.Response
import java.lang.Exception

object NetworkClassHelper {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): OperationStatus<T> {
        return try {
            val response = apiCall.invoke()
            OperationStatus.Success(response.body()!!)
        } catch (e: Exception) {
            OperationStatus.Failure(exception = e)
        }
    }

}