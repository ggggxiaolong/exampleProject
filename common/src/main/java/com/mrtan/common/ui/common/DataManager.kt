package com.mrtan.common.ui.common

import com.mrtan.common.ui.common.DataLoadingSubject.DataLoadingCallbacks
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

/**
 * @author mrtan on 8/30/17.
 */
class DataManager @Inject constructor() : DataLoadingSubject {
  internal val mLoadingCount: AtomicInteger = AtomicInteger(0)
  internal var mLoadingCallbacks: MutableList<DataLoadingSubject.DataLoadingCallbacks> = ArrayList<DataLoadingCallbacks>(1)

  override val isDataLoading: Boolean
    get() = mLoadingCount.get() > 0

  override fun registerCallback(callbacks: DataLoadingSubject.DataLoadingCallbacks) {
    mLoadingCallbacks.add(callbacks)
  }

  override fun unregisterCallback(callbacks: DataLoadingSubject.DataLoadingCallbacks) {
    if (mLoadingCallbacks.contains(callbacks)) {
      mLoadingCallbacks.remove(callbacks)
    }
  }

  fun loadStarted() {
    if (0 == mLoadingCount.getAndIncrement()) {
      dispatchLoadingStartedCallback()
    }
  }

  fun loadFinished() {
    if (0 == mLoadingCount.decrementAndGet()) {
      dispatchLoadingFinishedCallback()
    }
  }

  fun resetLoadingCount() {
    mLoadingCount.set(0)
  }

  protected fun dispatchLoadingStartedCallback() {
    for (callback in mLoadingCallbacks) {
      callback.dataStartedLoading()
    }
  }

  protected fun dispatchLoadingFinishedCallback() {
    for (callback in mLoadingCallbacks) {
      callback.dataFinishedLoading()
    }
  }
}