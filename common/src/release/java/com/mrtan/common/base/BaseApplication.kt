package com.mrtan.common.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mrtan.common.util.ContextHolder

/**
 * @author mrtan 17-3-28
 */

abstract class BaseApplication : Application(){

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  override fun onCreate() {
    super.onCreate()
    initializeUtil()
    initializeInject()
  }

  private fun initializeUtil() {
    ContextHolder.init(this)
  }

  abstract fun initializeInject()
}
