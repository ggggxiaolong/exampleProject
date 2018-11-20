package com.mrtan.common.inject

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Suppress("unused")
@Module
class CoroutineDispatcherModule {
  @Provides fun dispatchers(): CoroutineDispatcher = Dispatchers.IO
}