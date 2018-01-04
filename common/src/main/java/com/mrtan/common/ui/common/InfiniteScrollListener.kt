package com.mrtan.common.ui.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * @author mrtan on 8/30/17.
 */

abstract class InfiniteScrollListener(private val layoutManager: LinearLayoutManager,
    private val dataLoading: DataLoadingSubject) : RecyclerView.OnScrollListener() {

  override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
    // bail out if scrolling upward or already loading data
    if (dy < 0 || dataLoading.isDataLoading) return

    val visibleItemCount = recyclerView!!.childCount
    val totalItemCount = layoutManager.itemCount
    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

    if (totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
      onLoadMore()
    }
  }

  abstract fun onLoadMore()

  companion object {
    // The minimum number of items remaining before we should loading more.
    private val VISIBLE_THRESHOLD = 3
  }

}