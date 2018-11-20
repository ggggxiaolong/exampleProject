package com.mrtan.common.inject

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import com.mrtan.common.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection

/**
 * @author mrtan on 1/10/18.
 */
class AppInjector private constructor() {
  companion object {
    fun init(app: Application, fragmentInjector: AndroidInjector<Fragment>) {
      app.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
//          if (activity is BaseActivity) {
//            activity.fragmentInjector = fragmentInjector
//          }
          if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentLifecycleCallbacks() {
                  override fun onFragmentCreated(fm: FragmentManager, f: Fragment,
                      savedInstanceState: Bundle?) {
                    if (f is Injectable) {
                      AndroidSupportInjection.inject(f)
                    }
                  }
                }, true)
          }
        }

      })
    }
  }
}