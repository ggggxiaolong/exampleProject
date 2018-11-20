package com.mrtan.app.di

import com.mrtan.app.ui.CategoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author mrtan on 1/15/18.
 */
@Module(includes = [FragmentBuilderModule::class] )
abstract class ActivityModule {
  @ContributesAndroidInjector abstract fun category(): CategoryActivity
}