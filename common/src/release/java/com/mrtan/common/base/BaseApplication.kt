package com.mrtan.common.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mrtan.common.inject.AppInjector
import com.mrtan.common.util.ContextHolder
import javax.inject.Inject

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
    AppInjector.init(this)
  }

  private fun initializeUtil() {
    ContextHolder.init(this)
  }

  abstract fun initializeInject()
}
