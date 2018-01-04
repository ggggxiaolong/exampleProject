package com.mrtan.common.base

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.mrtan.common.inject.component.ApplicationComponent
import com.mrtan.common.inject.component.DaggerApplicationComponent
import com.mrtan.common.inject.module.ApplicationModule
import com.mrtan.common.util.ContextHolder
import com.mrtan.data.NetModule

/**
 * @author mrtan 17-3-28
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
