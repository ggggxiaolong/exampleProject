package com.zftx.pm.di

import com.zftx.pm.ui.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author mrtan on 1/15/18.
 */
@Module
abstract class FragmentBuilderModule {
  @ContributesAndroidInjector abstract fun contributeLogin(): LoginFragment
}