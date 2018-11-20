package com.mrtan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrtan.data.local.entity.UserEntity
import com.mrtan.data.local.entity.UserEntity.Companion.USER_TABLE

/**
 * @author mrtan on 1/17/18.
 */

@Dao
interface UserDao {
  @Query(value = "select * from $USER_TABLE")
  fun getUser(): List<UserEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(userEntity: UserEntity)
}