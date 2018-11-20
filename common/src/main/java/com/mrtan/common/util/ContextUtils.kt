package com.mrtan.common.util

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ContextHolder private constructor() {
  companion object {
    lateinit var context: Application
    fun init(value: Application) {
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
  return ContextCompat.getDrawable(ContextHolder.context, id)!!
}

fun dp2px(dpValue: Float): Int {
  val scale = ContextHolder.context.resources.displayMetrics.density
  return (dpValue * scale + 0.5f).toInt()
}