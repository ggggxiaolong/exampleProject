package com.mrtan.common.util

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.support.annotation.ArrayRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import com.mrtan.common.base.BasePresenter
import com.mrtan.common.base.MvpView

class ContextHolder private constructor() {
  companion object {
    lateinit var context: Application
    fun init(value: Application): Unit {
      context = value
    }
  }
}

/**
 * 全局获取String的方法
 * @param id 资源Id
 * @return String
 */
fun getString(@StringRes id: Int): String {
  return ContextHolder.context.resources.getString(id)
}

fun getStringArray(@ArrayRes id: Int): Array<out String> {
  return ContextHolder.context.resources.getStringArray(id)
}

fun getString(@StringRes id: Int, vararg formatArgs: Any): String {
  return ContextHolder.context.getString(id, *formatArgs)
}

fun getDrawable(@DrawableRes id: Int): Drawable {
  return ContextHolder.context.resources.getDrawable(id)
}

fun dp2px(dpValue: Float): Int {
  val scale = ContextHolder.context.resources.displayMetrics.density
  return (dpValue * scale + 0.5f).toInt()
}
/**
 * 判断App是否是Debug版本

 * @return `true`: 是<br></br>`false`: 否
 */
val isAppDebug: Boolean
  get() {
    val packageName = ContextHolder.context.packageName
    if (packageName == null || packageName.isEmpty()) return false
    try {
      val pm = ContextHolder.context.packageManager
      val ai = pm.getApplicationInfo(packageName, 0)
      return ai != null && ai.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    } catch (e: PackageManager.NameNotFoundException) {
      e.printStackTrace()
      return false
    }

  }

inline fun <reified V: MvpView, reified T : BasePresenter<V>> createViewModule(fragment: Fragment,
    factory: ViewModelProvider.Factory): T {
  val target = ViewModelProviders.of(fragment, factory).get(T::class.java)
  if (fragment is V) target.bind(fragment)
  return target
}