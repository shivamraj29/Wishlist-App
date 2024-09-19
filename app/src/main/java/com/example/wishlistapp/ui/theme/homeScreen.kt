package com.example.wishlistapp.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
//import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
//import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
//import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun homeScreen(navController: NavController,
               viewModel: MainViewModel){

    Scaffold(
        topBar = { TopBar(title = "Wishlist", {}) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.adddetailScreen.route + "/0L") },
                modifier = Modifier.padding(all = 20.dp),
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }}

    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .padding(it)
            ) {
        items(wishList.value, key = {wish-> wish.id}){data->


            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it== DismissValue.DismissedToEnd || it== DismissValue.DismissedToStart){
                        viewModel.deleteWish(data)
                    }
                    true
                }
            )

            SwipeToDismiss(state = dismissState,
                background = {
                    val color by animateColorAsState(
                        if(dismissState.dismissDirection== DismissDirection.EndToStart) Color.Red else
                        Color.Transparent
                    )
                    val alignment = Alignment.CenterEnd
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                    contentAlignment = alignment){
                        Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
                    }
                },
                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                dismissThresholds = {FractionalThreshold(0.25f)},
                dismissContent ={
                    showWish(data = data) {
                        val id = data.id
                        navController.navigate(Screen.adddetailScreen.route + "/$id")
                    }

                }
            )

        }
        }
    }
}

@Composable
fun showWish(data: Wish, onClick:()->Unit){

    Card(
        elevation = 6.dp,
        backgroundColor = Color(red = 56, green = 100, blue = 90),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if(data.title.isEmpty()){
                Text(text = "Add Wish", fontSize = 32.sp)
            }

        Text(text = data.title, fontWeight = FontWeight.SemiBold )
        Text(text = data.description)
    }}

}

