package com.mrtan.common.base

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import timber.log.Timber

/**
 * @author mrtan on 17-3-14.
 */

abstract class BaseFragment : Fragment() {

  protected fun showToast(message: String) {
    Timber.e(message)
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }

  protected fun showToast(@StringRes messageId: Int) {
    Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show()
  }

  fun hideSoftByEditViewIds(): IntArray {
    return IntArray(0)
  }
}
