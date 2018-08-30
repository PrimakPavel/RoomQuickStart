package com.primakpavel.roomsample.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE name=:name")
    fun getUserByName(name: String): LiveData<List<User>>

    @Insert
    fun insert(vararg users: User)

    @Update
    fun update(vararg users: User)

    @Delete
    fun delete(vararg users: User)
}
