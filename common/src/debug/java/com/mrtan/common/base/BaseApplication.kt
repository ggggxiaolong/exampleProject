package com.mrtan.common.base

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.mrtan.common.util.ContextHolder
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author mrtan on 8/30/17.
 */
abstract class BaseApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    initializeUtil()
    initializeInject()
    Timber.plant(DebugTree())
  }

  abstract fun initializeInject()

  private fun initializeUtil() {
    ContextHolder.init(this)
    Utils.init(this)
  }
}
