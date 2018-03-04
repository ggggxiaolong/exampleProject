package com.mrtan.common.util

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun scheduler(): Scheduler {
  return Schedulers.from(ExecutorManager.eventExecutor)
}

fun <T> Observable<T>.async(): Observable<T> {
  return subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
      .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single <T>.async(): Single<T> {
  return subscribeOn(Schedulers.from(ExecutorManager.eventExecutor))
      .observeOn(AndroidSchedulers.mainThread())
}

//inline fun Disposable.addThis(t: (Disposable) -> Unit) = t(this)