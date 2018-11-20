package com.mrtan.data.remote

import com.mrtan.data.remote.Result.Empty
import com.mrtan.data.remote.Result.Loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

sealed class Result<out T> {

  class Success<out T>(
    val value: T
  ) : Result<T>()

  class Error(
    val exception: Throwable
  ) : Result<Nothing>() {
    fun message(): String = exception.message ?: "unknown error"
  }

  object Loading : Result<Nothing>()

  object Empty : Result<Nothing>()

  fun <R> map(mapper: (T) -> R): Result<R> {
    return when (this) {
      is Success -> Success(mapper(value))
      is Loading -> this
      is Error -> this
      is Empty -> this
    }
  }
}

inline fun <T> CoroutineScope.exec(
  crossinline onData: (Result<T>) -> Unit,
  crossinline run: suspend () -> T
) {
  onData(Result.Loading)
  launch {
    try {
      val data = run()
      withContext(Dispatchers.Main) {
        onData(Result.Success(data))
      }
    } catch (e: Throwable) {
      withContext(Dispatchers.Main) {
        Timber.e(e)
        onData(Result.Error(e))
      }
    }
  }
}

inline fun <T> CoroutineScope.exec2(
  crossinline onData: (Result<T>) -> Unit,
  crossinline run: () -> T
) {
  onData(Loading)
  launch {
    try {
      val data = run()
      withContext(Dispatchers.Main) {
        onData(Result.Success(data))
        onData(Empty)
      }
    } catch (e: Throwable) {
      withContext(Dispatchers.Main) {
        onData(Result.Error(e))
        onData(Empty)
      }
    }
  }

}