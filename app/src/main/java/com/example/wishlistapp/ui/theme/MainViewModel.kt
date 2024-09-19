package com.example.wishlistapp.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository
): ViewModel() {

var titileInput by mutableStateOf("")
    var descInput by mutableStateOf("")


    fun onTitleChange(newTitle:String){
        titileInput= newTitle
    }

    fun onDescChange(newDesc:String){
        descInput = newDesc
    }

    lateinit var getAllWishes:Flow<List<Wish>>

    init {
        viewModelScope.launch {
           getAllWishes= wishRepository.getAllWishes()
        }
    }
    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }
    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun getAWishById(id:Long):Flow<Wish>{
       return wishRepository.getAWishById(id)
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}
