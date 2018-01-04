package com.mrtan.common.inject.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author mrtan on 17-3-13.
 */
@Module class ApplicationModule(internal val mApplication: Application) {

  @Provides @Singleton internal fun provideApplication(): Context {
    return mApplication
  }

  @Provides @Singleton internal fun provideApp(): Application {
    return mApplication
  }
}
