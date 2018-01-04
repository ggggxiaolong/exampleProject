package com.mrtan.common.base

import dagger.internal.Preconditions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author mrtan on 17-3-15.
 */

open class BasePresenter<V : MvpView> : MvpPresenter<V> {
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

  protected fun addSubscription(subscription: Disposable) {
    mSubscription.add(subscription)
  }

  protected fun dispose() {
    mSubscription.dispose()
  }
}
