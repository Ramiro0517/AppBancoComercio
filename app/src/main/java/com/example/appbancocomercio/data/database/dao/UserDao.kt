package com.example.appbancocomercio.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appbancocomercio.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
     fun getAllUsers(): List<UserEntity?>

    @Query("SELECT * FROM user_table where userName = :email and password = :password")
    suspend  fun getUsers( email:String?, password:String?): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun  insertAll(users:List<UserEntity?>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun  insert(users: UserEntity)

    @Query("DELETE  FROM user_table")
    suspend fun deleteAll()

}