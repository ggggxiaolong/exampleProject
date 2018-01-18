package com.mrtan.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
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