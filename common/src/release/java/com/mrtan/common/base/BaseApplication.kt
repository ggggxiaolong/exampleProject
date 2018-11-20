package com.mrtan.common.base

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.mrtan.common.util.ContextHolder

/**
 * @author mrtan 17-3-28
 */

abstract class BaseApplication : Application(){

  override fun onCreate() {
    super.onCreate()
    initializeUtil()
    initializeInject()
  }

  private fun initializeUtil() {
    ContextHolder.init(this)
    Utils.init(this)
  }

  abstract fun initializeInject()
}
