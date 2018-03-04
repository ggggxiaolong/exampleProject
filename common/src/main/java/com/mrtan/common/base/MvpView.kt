package com.mrtan.common.base

import android.arch.lifecycle.LifecycleOwner

/**
 * @author mrtan on 17-3-15.
 */

interface MvpView : LifecycleOwner {
  fun onError(message: String)
}
