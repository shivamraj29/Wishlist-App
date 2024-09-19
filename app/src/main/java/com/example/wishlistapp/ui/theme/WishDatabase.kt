package com.example.wishlistapp.ui.theme

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)



abstract class WishDatabase:RoomDatabase() {
    abstract fun WishDao():WishDao
}