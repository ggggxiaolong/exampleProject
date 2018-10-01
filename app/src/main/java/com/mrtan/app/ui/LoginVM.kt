package com.mrtan.app.ui

import com.mrtan.common.base.BasePresenter
import com.mrtan.common.util.async
import com.mrtan.data.Repository
import com.tencent.bugly.crashreport.CrashReport
import com.uber.autodispose.kotlin.autoDisposable
import io.reactivex.Observer
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class LoginVM @Inject constructor(private val mRepository: Repository) : BasePresenter<LoginView>() {

  fun showText(observer: Observer<String>) {
    Single.create<String> { e -> e.onSuccess("hello world") }
        .autoDisposable(scopeProvide)
        .subscribe(observer::onNext)
  }

  fun login(
    name: String,
    password: String
  ) {
    mRepository.login(name, password)
        .async()
        .autoDisposable(scopeProvide)
        .subscribe({ token -> mView.onLogin(token) },
            { error -> mView.onLogin(error.stackTrace.toString()) })
  }

  fun user() {
    mRepository.user("drakeet")
        .async()
        .doOnError {
          CrashReport.postCatchedException(it)
          it.printStackTrace()
        }
        .autoDisposable(scopeProvide)
        .subscribe(
            { user -> mView.onLogin(user.toString()) },
            { error -> mView.onError(error.message ?: "") })
  }
}