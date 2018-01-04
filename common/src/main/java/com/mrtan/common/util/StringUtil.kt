package com.mrtan.common.util

/**
 * @author mrtan 17-3-30
 */

fun ByteArray.toHexString(): String {
  val sb = StringBuilder(size)
  forEach {
    val value = Integer.toHexString(0xFF and it.toInt())
    if (value.length == 1) sb.append("0")
    sb.append(value.toUpperCase())
  }
  return sb.toString()
}
