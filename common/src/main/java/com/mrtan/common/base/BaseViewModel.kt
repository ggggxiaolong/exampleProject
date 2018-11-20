package com.mrtan.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(dispatcher: CoroutineDispatcher) : ViewModel(), CoroutineScope {
  protected val job = Job()
  override val coroutineContext: CoroutineContext = dispatcher + job
  override fun onCleared() {
    job.cancel()
  }
}