package com.primakpavel.roomsample.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context

@Database(entities = [User::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {

        private const val DB_NAME = "userDatabase.db"
        @Volatile
        private var instance: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = create(context)
                }

            }
            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME)
                    .addMigrations(MIGRATION_2_3) // add specific migration rule
                    //.fallbackToDestructiveMigration()// if you want clear table after migration
                    .build()
        }


        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users " + " ADD COLUMN age INTEGER") // check Entity .!!! Your new column value can be "null"
            }
        }
    }
}
