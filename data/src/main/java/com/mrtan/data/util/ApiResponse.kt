package com.mrtan.data.util

import com.mrtan.data.util.ApiResponse.State.ERROR
import com.mrtan.data.util.ApiResponse.State.FAIL
import com.mrtan.data.util.ApiResponse.State.SUCCESS
import retrofit2.Response

sealed class ApiResponse<T>(val state: State) {

  class ApiSuccess<T>(val body: T) : ApiResponse<T>(SUCCESS)

  class ApiError<T>(val error: Throwable) : ApiResponse<T>(ERROR)

  class ApiFail<T>(
    val message: String,
    val code: Int
  ) : ApiResponse<T>(FAIL)

  enum class State {
    SUCCESS,
    ERROR,
    FAIL
  }

  companion object {
    fun <T> create(error: Throwable) = ApiError<T>(error)

    fun <T> create(response: Response<T>): ApiResponse<T>{
      return if (response.isSuccessful) {
        ApiSuccess(response.body()!!)
      } else {
        val msg = response.errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) {
          response.message()
        } else {
          msg
        }
        ApiFail(errorMsg ?: "unknown error", response.code())
      }
    }

  }
}