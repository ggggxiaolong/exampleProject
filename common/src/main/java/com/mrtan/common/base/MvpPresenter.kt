package com.mrtan.common.base

/**
 * @author mrtan on 17-3-15.
 */

interface MvpPresenter<in V : MvpView> {
  fun onAttach(view: V)

  fun onDetach()
}