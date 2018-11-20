package com.mrtan.app.di

import android.app.Application
import com.mrtan.app.App
import com.mrtan.common.inject.CoroutineDispatcherModule
import com.mrtan.data.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author mrtan on 1/17/18.
 */

@Singleton
@Component(
    modules = [
      AndroidInjectionModule::class,
      NetModule::class,
      ActivityModule::class,
      ViewModelModule::class,
      CoroutineDispatcherModule::class
    ]
)
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance fun application(app: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}
