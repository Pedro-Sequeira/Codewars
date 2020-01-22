package com.example.android.codewars.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.codewars.models.User

@Dao
interface UserDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: DatabaseUser)

    @Update
    fun update(user: User)

    @Query("SELECT * from user_search_history_table WHERE username = :username")
    fun get(username: String): User?

    @Query("SELECT * FROM user_search_history_table LIMIT 5")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user_search_history_table")
    fun clear()
}