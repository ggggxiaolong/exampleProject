package com.mrtan.common.base

import android.arch.lifecycle.ViewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import dagger.internal.Preconditions

/**
 * @author mrtan on 17-3-15.
 */

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>, ViewModel() {
  protected lateinit var mView: V
  protected val scopeProvide: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(mView) }

  override fun bind(view: V) {
    Preconditions.checkNotNull(view)
    mView = view
  }
}
