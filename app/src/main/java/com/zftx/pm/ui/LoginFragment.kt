package com.zftx.pm.ui

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrtan.common.base.BaseFragment
import com.mrtan.common.inject.Injectable
import com.mrtan.common.util.createViewModel
import com.zftx.pm.R
import com.zftx.pm.databinding.FragLoinBinding
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class LoginFragment: BaseFragment(), Injectable, LoginView {
  lateinit var mBinding: FragLoinBinding
  lateinit var mViewModel: LoginVM
  @Inject lateinit var mVMFactory: ViewModelProvider.Factory

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    mBinding = DataBindingUtil.inflate(inflater, R.layout.frag_loin, container, false)
    return mBinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    mViewModel = createViewModel(this, mVMFactory)
    mBinding.loginVM = mViewModel
    mViewModel.showText(object : Observer<String> {
      override fun onError(e: Throwable) {
      }

      override fun onComplete() {
      }

      override fun onNext(t: String) {
        mBinding.setText(t)
      }

      override fun onSubscribe(d: Disposable) {
      }

    })
  }

  override fun onLogin(token: String) {
    showToast("登录成功")
  }

  override fun onError(message: String) {
    showToast(message)
  }

  companion object {
    fun newInstance(): LoginFragment {
      return LoginFragment()
    }
  }
}