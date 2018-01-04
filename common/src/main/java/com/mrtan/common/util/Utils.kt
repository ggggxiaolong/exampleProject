package com.mrtan.common.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.support.annotation.ArrayRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

class ContextHolder private constructor() {
  companion object {
    lateinit var context: Context
    fun init(value: Context): Unit {
      context = value.applicationContext
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