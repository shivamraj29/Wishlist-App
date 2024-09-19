package com.example.wishlistapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun navigation(NavController: NavHostController = rememberNavController(),
               viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){
    NavHost(navController = NavController, startDestination = Screen.homeScreen.route) {

        composable(Screen.homeScreen.route){
            homeScreen(NavController, viewModel
            )
        }
        composable(Screen.adddetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type= NavType.LongType
                    defaultValue = 0L
                    nullable= false
                }
            )
        ){  entry->
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            viewAddDetail(id = id, viewModel = viewModel, navController = NavController)
        }
    }
}