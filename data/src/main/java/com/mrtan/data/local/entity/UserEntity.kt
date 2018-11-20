package com.mrtan.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mrtan.data.local.entity.UserEntity.Companion.USER_TABLE

/**
 * @author mrtan on 1/17/18.
 */

@Entity(tableName = USER_TABLE)
data class UserEntity constructor(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int, //
    @ColumnInfo(name = TOKEN) val token: String
) {
  companion object {
    const val USER_TABLE = "user"
    const val ID = "_id"
    const val TOKEN = "token"

    operator fun invoke(token: String) = UserEntity(
        id = 1,
        token = token
    )
  }
}