package com.mrtan.common.util

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.mrtan.common.base.BaseActivity

/**
 * @author mrtan 17-4-5
 */

fun TextView.setAnyText(value: Any) {
  text = value.toString()
}

fun View.isVisible(): Boolean {
  return visibility == View.VISIBLE
}

fun Context.showToast(message: String){
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun BaseActivity.showBack(){
  supportActionBar?.setDisplayShowHomeEnabled(true)
  supportActionBar?.setDisplayHomeAsUpEnabled(true)
}
