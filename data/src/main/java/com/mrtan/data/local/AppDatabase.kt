package com.mrtan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrtan.data.local.dao.UserDao
import com.mrtan.data.local.entity.UserEntity

/**
 * @author mrtan on 8/30/17.
 */
@Database(
    entities = [UserEntity::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
  companion object {
    val DATABASE_NAME: String = "pm-db"
  }

  abstract fun userDao(): UserDao

}