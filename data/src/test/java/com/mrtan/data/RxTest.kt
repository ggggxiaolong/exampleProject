package com.mrtan.data

import io.reactivex.Observable
import org.junit.Test

/**
 * @author mrtan on 11/9/17.
 */
class RxTest {
  @Test
  fun testIt() {
    val arr = Array<Int>(5, { it })

    Observable.just(arr).map { a ->
      Array<Int>(a.size, { a[it] + 1 })//这里怎么写？怎么区分多个it？
    }.subscribe { ret -> assert(ret.size == 5) }
  }
}