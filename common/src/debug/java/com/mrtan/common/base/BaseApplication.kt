package com.mrtan.common.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mrtan.common.inject.component.ApplicationComponent
import com.mrtan.common.inject.component.DaggerApplicationComponent
import com.mrtan.common.inject.module.ApplicationModule
import com.mrtan.common.util.ContextHolder
import com.mrtan.data.NetModule
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author mrtan on 8/30/17.
 */
open class BaseApplication : Application() {
  lateinit var applicationComponent: ApplicationComponent
    internal set

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

  private fun initializeUtil() {
    ContextHolder.init(this)
  }

  private fun initializeInject() {
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .netModule(NetModule())
        .build()
  }
}
