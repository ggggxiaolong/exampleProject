package com.zftx.pm.ui

import com.mrtan.common.base.BasePresenter
import com.mrtan.common.util.addThis
import com.mrtan.common.util.async
import com.mrtan.data.Repository
import io.reactivex.Observer
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class LoginVM @Inject constructor(private val mRepository: Repository)
  : BasePresenter<LoginView>() {

  fun showText(observer: Observer<String>) {
    Single.create<String> { e -> e.onSuccess("hello world") }.subscribe(
        observer::onNext).addThis { addSubscription(it) }
  }

  fun login(name: String, password: String) {
    mRepository.login(name, password).async().subscribe({ token -> mView?.onLogin(token) },
        { error -> mView?.onError(error.message ?: "") }).addThis { addSubscription(it) }
  }
}