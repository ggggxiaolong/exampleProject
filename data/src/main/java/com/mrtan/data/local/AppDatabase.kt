package com.mrtan.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * @author mrtan on 8/30/17.
 */
/*@Database(
    entities = [],
    version = 1)*/
abstract class AppDatabase : RoomDatabase() {
  companion object {
    val DATABASE_NAME: String = "pm-db"
  }

}