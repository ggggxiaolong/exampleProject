@file:JvmName("StringUtil")

package com.mrtan.common.util

/**
 * @author mrtan 17-3-30
 */

fun Array<Byte>.toHex(): String {
  val sb = StringBuilder()
  this.forEach {
    sb.append(String.format("%02x", it))
  }
  return sb.toString()
}

fun ByteArray.toHex(): String {
  val sb = StringBuilder()
  this.forEach {
    sb.append(String.format("%02x", it))
  }
  return sb.toString()
}

fun String.hex2TypeArray(): Array<Byte> {
  return Array<Byte>(length / 2) {
    val index = it * 2
    this.substring(index..index + 1)
        .toInt(16)
        .toByte()
  }
}

fun String.hex2ByteArray(): ByteArray {
  return Array<Byte>(length / 2) {
    val index = it * 2
    this.substring(index..index + 1)
        .toInt(16)
        .toByte()
  }.toByteArray()
}