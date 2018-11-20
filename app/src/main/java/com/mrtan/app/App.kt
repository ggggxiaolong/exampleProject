package com.mrtan.app

import android.app.Activity
import com.mrtan.app.di.DaggerAppComponent
import com.mrtan.common.base.BaseApplication
import com.tencent.bugly.crashreport.CrashReport
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class App : BaseApplication(), HasActivityInjector {
  @Inject lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

  override fun activityInjector(): AndroidInjector<Activity> {
    return mActivityInjector
  }

  override fun onCreate() {
    super.onCreate()
    CrashReport.initCrashReport(this)
  }

  override fun initializeInject() {
    val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()
    appComponent.inject(this)
  }
}