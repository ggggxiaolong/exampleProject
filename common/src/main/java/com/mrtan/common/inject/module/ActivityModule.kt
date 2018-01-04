package com.mrtan.common.inject.module

import android.app.Activity
import com.mrtan.common.inject.ScopeActivity
import dagger.Module
import dagger.Provides

/**
 * @author mrtan on 17-3-14.
 */
@Module class ActivityModule(internal val mActivity: Activity) {

  @Provides @ScopeActivity internal fun provideActivity(): Activity {
    return mActivity
  }
}
