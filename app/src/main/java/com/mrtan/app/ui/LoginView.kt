package com.mrtan.app.ui

import com.mrtan.common.base.MvpView

/**
 * @author mrtan on 1/18/18.
 */
interface LoginView: MvpView {
  fun onLogin(token: String)
}