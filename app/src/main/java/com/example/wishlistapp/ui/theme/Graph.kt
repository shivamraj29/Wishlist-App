package com.example.wishlistapp.ui.theme

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object Graph {

    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.WishDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}