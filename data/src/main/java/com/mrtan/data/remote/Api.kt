package com.mrtan.data.remote

import com.mrtan.data.BuildConfig

/**
 * @author mrtan on 8/30/17.
 */
interface Api {

  companion object {
    val BASE_URL: String = BuildConfig.API
  }
}