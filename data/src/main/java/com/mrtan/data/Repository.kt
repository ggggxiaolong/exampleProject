package com.mrtan.data

import com.mrtan.data.local.AppDatabase
import com.mrtan.data.remote.Api
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author mrtan on 8/30/17.
 */
@Singleton
class Repository @Inject constructor(
  private val mDB: AppDatabase,//
  private val mApi: Api
) {
  fun category(author: String) = mApi.categories(author)
}

suspend inline fun <T>  Deferred<T>.bindWait(job: Job) = let {
  job.invokeOnCompletion { if (isCancelled) this.cancel() }
  await()
}