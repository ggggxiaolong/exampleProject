package com.mrtan.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ToastUtils
import com.mrtan.app.R
import com.mrtan.app.databinding.FragLoinBinding
import com.mrtan.common.base.BaseFragment
import com.mrtan.common.inject.Injectable
import com.mrtan.data.remote.Result
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class CategoryFragment : BaseFragment(), Injectable {
  lateinit var binding: FragLoinBinding
  @Inject lateinit var factory: ViewModelProvider.Factory
  lateinit var viewModel: CategoryViewModel

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.frag_loin, container, false)
    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this, factory)
        .get(CategoryViewModel::class.java)
    binding.login.setOnClickListener {
      viewModel.category("drakeet")
    }
    viewModel.categories.observe(this, Observer {
      when (it) {
        is Result.Success -> {
          if (it.value.isNotEmpty()) {
            binding.text.text = it.value[0].name
          }
        }
        is Result.Error -> ToastUtils.showShort(it.message())
      }
    })
  }

  companion object {
    fun newInstance(): CategoryFragment {
      return CategoryFragment()
    }
  }
}