package com.zftx.pm

import android.app.Activity
import com.mrtan.common.base.BaseApplication
import com.mrtan.common.inject.AppInjector
import com.zftx.pm.di.DaggerAppComponent
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

  override fun initializeInject() {
    DaggerAppComponent.builder().application(this).build().inject(this)
    AppInjector.init(this)
  }
}