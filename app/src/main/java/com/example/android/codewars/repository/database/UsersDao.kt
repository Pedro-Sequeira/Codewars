package com.example.android.codewars.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserDB?)

    @Query("SELECT * from user_search_history_table WHERE username = :username")
    fun getUser(username: String?): UserDB?

    @Query("SELECT * FROM user_search_history_table ORDER BY creationDate DESC LIMIT 5")
    fun getAllUsers(): LiveData<List<UserDB>>
}