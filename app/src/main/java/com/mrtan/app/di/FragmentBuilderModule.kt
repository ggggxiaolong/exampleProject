package com.mrtan.app.di

import com.mrtan.app.ui.CategoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author mrtan on 1/15/18.
 */
@Module
abstract class FragmentBuilderModule {
  @ContributesAndroidInjector abstract fun category(): CategoryFragment
}