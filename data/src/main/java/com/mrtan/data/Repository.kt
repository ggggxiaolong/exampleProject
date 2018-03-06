package com.mrtan.data

import android.os.SystemClock
import com.mrtan.data.domain.User
import com.mrtan.data.local.AppDatabase
import com.mrtan.data.local.entity.UserEntity
import com.mrtan.data.remote.Api
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author mrtan on 8/30/17.
 */
@Singleton
class Repository @Inject constructor(
    private val mDB: AppDatabase,//
    private val mApi: Api) {

  fun login(name: String, password: String): Single<String> {
    return Single.create<String> { e ->
      kotlin.run {
        SystemClock.sleep(1000)
        e.onSuccess("sadasdasdwq123daq23455we65wqe")
      }
    }.doOnSuccess { token ->
      Schedulers.io().createWorker().schedule {
        mDB.userDao().insert(UserEntity.invoke(token))
      }
    }
  }

  fun user(username: String): Single<User> = mApi.user(username)
}