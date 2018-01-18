package com.mrtan.common.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.mrtan.common.R
import com.mrtan.common.util.KeyBoardUtils
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * @author mrtan on 17-3-14.
 */
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

  internal var mTitle: TextView? = null
  @Inject
  lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  protected fun addFragment(@IdRes containerViewId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction().add(containerViewId, fragment).commit()
  }

  override fun setContentView(@LayoutRes layoutResID: Int) {
    super.setContentView(layoutResID)
    val view:View? = findViewById(R.id.toolbar)
    if (view != null) {
      val toolbar = view as Toolbar
      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayShowTitleEnabled(false)
      mTitle = toolbar.findViewById(R.id.toolbar_title)
    }
  }

  override fun onTitleChanged(title: CharSequence, color: Int) {
    if (mTitle != null) {
      mTitle?.text = title
    } else {
      super.onTitleChanged(title, color)
    }
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
          KeyBoardUtils.hideInputForce(this)
          v.clearFocus()
          break
        }
      }
    }
    return super.dispatchTouchEvent(ev)
  }

  fun isFocusView(v: View, vararg ids: Int): Boolean {
    return (ids.isNotEmpty()) && ids.contains(v.id)
  }

  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return mDispatchingAndroidInjector
  }

  companion object {
    init {
      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
  }
}
