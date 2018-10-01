package com.mrtan.app.ui

import android.os.Bundle
import com.mrtan.common.base.BaseActivity
import com.mrtan.app.R

/**
 * @author mrtan on 1/15/18.
 */
class LoginActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_common)
    if (savedInstanceState == null) {
      addFragment(R.id.container, LoginFragment.newInstance())
    }
  }
}