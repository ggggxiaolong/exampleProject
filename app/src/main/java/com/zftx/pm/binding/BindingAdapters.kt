package com.zftx.pm.binding

import android.content.Context
import android.databinding.BindingAdapter
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
  get() = visibility == VISIBLE
  set(value) {
    visibility = if (value) VISIBLE else GONE
  }

@set:BindingAdapter("visible")
var View.visible
  get() = visibility == VISIBLE
  set(value) {
    visibility = if (value) VISIBLE else INVISIBLE
  }

@set:BindingAdapter("invisible")
var View.invisible
  get() = visibility == INVISIBLE
  set(value) {
    visibility = if (value) INVISIBLE else VISIBLE
  }

@set:BindingAdapter("gone")
var View.gone
  get() = visibility == GONE
  set(value) {
    visibility = if (value) GONE else VISIBLE
  }

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
  Glide.with(context).load(url).into(this)
}

@BindingAdapter("onSearch")
fun onSearch(input: EditText, listener: OnTextListener?) {
  input.setOnEditorActionListener({ v, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
      if (listener != null) {
        val query = input.text.toString()
        v.dismissKeyboard()
        listener.onChanged(query)
      }
      true
    } else {
      false
    }
  })
}

@BindingAdapter("onKeyDown")
fun onKeyDown(input: EditText, listener: OnTextListener?) {
  input.setOnKeyListener { _, keyCode, event ->
    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
      if (listener != null) {
        val query = input.text.toString()
        input.dismissKeyboard()
        listener.onChanged(query)
      }
      true
    } else {
      false
    }
  }
}

interface OnTextListener {
  fun onChanged(s: String)
}

fun TextView.dismissKeyboard() {
  if (context != null) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
  }
}