package com.mrtan.common.ui.common

/**
 * @author mrtan on 8/30/17.
 */
interface DataLoadingSubject {
  val isDataLoading: Boolean
  fun registerCallback(callbacks: DataLoadingCallbacks)
  fun unregisterCallback(callbacks: DataLoadingCallbacks)

  interface DataLoadingCallbacks {
    fun dataStartedLoading()
    fun dataFinishedLoading()
  }
}