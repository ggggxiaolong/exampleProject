package com.mrtan.common.base

import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.KeyboardUtils

/**
 * @author mrtan on 17-3-14.
 */
abstract class BaseActivity : AppCompatActivity() {

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerViewId, fragment)
        .commit()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == android.R.id.home) {
      onBackPressed()
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
    val v = currentFocus ?: return super.dispatchTouchEvent(ev)
    val fragments = supportFragmentManager.fragments
    for (fragment in fragments) {
      if (fragment.isVisible && fragment is BaseFragment) {
        val ids = fragment.hideSoftByEditViewIds()
        if (isFocusView(v, *ids)) {
          KeyboardUtils.hideSoftInput(this)
          v.clearFocus()
          break
        }
      }
    }
    return super.dispatchTouchEvent(ev)
  }

  fun isFocusView(
    v: View,
    vararg ids: Int
  ): Boolean {
    val iterator = ids.iterator()
    while (iterator.hasNext()) {
      if (iterator.nextInt() == v.id) {
        return true
      }
    }
    return false
  }

  companion object {
    init {
      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
  }
}
