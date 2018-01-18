package com.mrtan.common.base

import android.arch.lifecycle.ViewModel
import dagger.internal.Preconditions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author mrtan on 17-3-15.
 */

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>, ViewModel() {
  protected var mView: V? = null
  internal var mSubscription = CompositeDisposable()

  override fun onAttach(view: V) {
    Preconditions.checkNotNull(view)
    mView = view
  }

  override fun onDetach() {
    dispose()
    mView = null
  }

  override fun onCleared() {
    onDetach()
  }

  protected fun addSubscription(subscription: Disposable) {
    mSubscription.add(subscription)
  }

  protected fun dispose() {
    mSubscription.dispose()
  }
}
