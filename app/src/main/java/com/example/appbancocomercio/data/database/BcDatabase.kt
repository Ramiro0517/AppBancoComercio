package com.example.appbancocomercio.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appbancocomercio.data.database.dao.UserDao
import com.example.appbancocomercio.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class BcDatabase:RoomDatabase() {

    abstract fun userDao(): UserDao
}