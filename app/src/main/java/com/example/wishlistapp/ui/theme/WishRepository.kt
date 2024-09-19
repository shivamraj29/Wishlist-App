package com.example.wishlistapp.ui.theme

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    fun getAWishById(id:Long):Flow<Wish>{
        return wishDao.getWishByID(id)}

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }
}