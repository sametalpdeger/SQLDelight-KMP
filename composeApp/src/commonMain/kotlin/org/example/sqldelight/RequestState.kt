package org.example.sqldelight

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data class Loading<T>(val data: T? = null) : RequestState<T>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val exception: Exception) : RequestState<Nothing>()

    fun isLoading(): Boolean = this is Loading
    fun isError(): Boolean = this is Error
    fun isSuccess(): Boolean = this is Success

    fun getSuccessData() = (this as Success).data
    fun getErrorMessage(): String = (this as Error).exception.message.toString()
}