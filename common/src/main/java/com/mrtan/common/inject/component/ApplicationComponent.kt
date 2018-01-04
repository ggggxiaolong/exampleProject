package com.mrtan.common.inject.component

import android.content.Context
import com.mrtan.common.inject.module.ApplicationModule
import com.mrtan.data.NetModule
import com.mrtan.data.Repository
import dagger.Component
import javax.inject.Singleton

/**
 * @author mrtan on 17-3-14.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))
interface ApplicationComponent {
  val context: Context

  val repository: Repository
}
