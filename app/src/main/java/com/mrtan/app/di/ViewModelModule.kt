package com.mrtan.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrtan.app.ui.CategoryViewModel
import com.mrtan.common.inject.ViewModelKey
import com.mrtan.common.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author mrtan on 1/17/18.
 */

@Suppress("unused")
@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(CategoryViewModel::class)
  abstract fun category(model: CategoryViewModel): ViewModel

  @Binds abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory
}
