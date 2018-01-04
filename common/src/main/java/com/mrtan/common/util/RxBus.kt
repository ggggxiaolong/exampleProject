package com.mrtan.common.util

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject


/**
 * @author mrtan on 6/27/17.
 */
object RxBus {
  private val TAG = javaClass.simpleName

  /**
   * Used to hold all subscriptions for Bus events and unsubscribe properly when needed.
   */
  private val subscriptionMap: HashMap<Any, CompositeDisposable?> by lazy { HashMap<Any, CompositeDisposable?>() }

  /**
   * Avoid using this property directly, exposed only because it's used inline fun
   */
  val bus: BehaviorSubject<Any> = BehaviorSubject.create()

  /**
   * Send an event to Bus. Can be called from any thread
   */
  fun send(event: Any) {
    bus.onNext(event)
  }

  /**
   * Subscribes for events of certain type T. Can be called from any thread
   */
  inline fun <reified T : Any> observe(): Observable<T> {
    return bus.ofType(T::class.java)
  }

  /**
   * Unregisters subscriber from Bus events.
   * Calls unsubscrib method of your subscription
   * @param subscriber subscriber to unregister
   */
  fun unregister(subscriber: Any) {
    val compositeSubscription = subscriptionMap[subscriber]
    if (compositeSubscription == null) {
      Log.w(TAG, "Trying to unregister subsriber that wasn't registered")
    } else {
      compositeSubscription.clear()
      subscriptionMap.remove(subscriber)
    }
  }

  internal fun register(subscriber: Any, subscription: Disposable) {
    var compositeSubscription = subscriptionMap[subscriber]
    if (compositeSubscription == null) {
      compositeSubscription = CompositeDisposable()
    }
    compositeSubscription.add(subscription)
    subscriptionMap[subscriber] = compositeSubscription
  }
}

/**
 * Register the subscription to correctly unregister it later to avoid memory leaks
 * @param subscriber subscriber object that owns this subscription
 */
fun Disposable.registerInBus(subscriber: Any) {
  RxBus.register(subscriber, this)
}