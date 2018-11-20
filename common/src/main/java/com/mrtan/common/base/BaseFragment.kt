package com.mrtan.common.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import timber.log.Timber

/**
 * @author mrtan on 17-3-14.
 */

abstract class BaseFragment : Fragment() {

  fun hideSoftByEditViewIds(): IntArray {
    return IntArray(0)
  }
}
