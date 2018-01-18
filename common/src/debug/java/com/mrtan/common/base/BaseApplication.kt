package com.mrtan.common.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mrtan.common.util.ContextHolder
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author mrtan on 8/30/17.
 */
abstract class BaseApplication : Application() {

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  override fun onCreate() {
    super.onCreate()
    initializeUtil()
    initializeInject()
    Timber.plant(DebugTree())
  }

  abstract fun initializeInject()

  private fun initializeUtil() {
    ContextHolder.init(this)
  }
}
