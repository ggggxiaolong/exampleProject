package com.mrtan.common.util

import com.mrtan.common.base.BaseActivity

/**
 * @author mrtan 17-4-5
 */

fun BaseActivity.showBack(){
  supportActionBar?.setDisplayShowHomeEnabled(true)
  supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
