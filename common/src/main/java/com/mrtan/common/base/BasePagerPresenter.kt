package com.mrtan.common.base

import com.mrtan.common.ui.common.DataManager
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

abstract class BasePagerPresenter<V : MvpView>(val dataManager: DataManager) : BasePresenter<V>() {
  private var mPageNumber = AtomicInteger(1)
  protected var mHasMore = AtomicBoolean(true)
  protected val pageSize = 15

  abstract fun load(isFirst: Boolean = false)

  protected fun clearData(pageNumber: Int = 1) {
    mPageNumber.set(pageNumber)
    mHasMore.set(true)
  }

  protected val nextPageNumber: Int
    get() = mPageNumber.getAndIncrement()

  protected val currentNumber: Int
    get() = mPageNumber.get()

  protected fun reducePageNumber() {
    if (mPageNumber.get() > 1) mPageNumber.decrementAndGet()
    mHasMore.set(false)
  }
}