package com.mrtan.data.remote

import com.mrtan.data.BuildConfig
import com.mrtan.data.domain.Category
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author mrtan on 8/30/17.
 * https://github.com/drakeet/rebase-api
 */
interface Api {

  companion object {
    const val BASE_URL: String = BuildConfig.API
  }

  @GET("categories/{author}")
  fun categories(@Path("author") author: String): Deferred<List<Category>>

}