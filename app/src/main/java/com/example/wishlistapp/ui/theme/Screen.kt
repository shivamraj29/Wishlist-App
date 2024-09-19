package com.example.wishlistapp.ui.theme

sealed class Screen(val route:String) {
    object homeScreen: Screen(route = "homescreen")
    object adddetailScreen:Screen(route = "adddetailscreen")
}