package com.zftx.pm.di

import com.mrtan.common.base.BaseActivity
import com.zftx.pm.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author mrtan on 1/15/18.
 */
@Module(includes = [FragmentBuilderModule::class] )
abstract class ActivityModule {
}