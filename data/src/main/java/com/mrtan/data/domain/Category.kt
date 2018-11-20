package com.mrtan.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Category(
  val _id: String,
  val key: String,
  val name: String,
  val rank: Float
)