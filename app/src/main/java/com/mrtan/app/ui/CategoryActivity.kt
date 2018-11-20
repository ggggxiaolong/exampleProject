package com.mrtan.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mrtan.common.base.BaseActivity
import com.mrtan.app.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author mrtan on 1/15/18.
 */
class CategoryActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject lateinit var fragmentInject: DispatchingAndroidInjector<Fragment>
  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInject

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    AndroidInjection.inject(this)
    setContentView(R.layout.activity_common)
    if (savedInstanceState == null) {
      addFragment(R.id.container, CategoryFragment.newInstance())
    }
  }
}