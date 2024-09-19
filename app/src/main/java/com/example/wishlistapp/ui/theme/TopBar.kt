package com.example.wishlistapp.ui.theme

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
//import androidx.compose.material.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String,
            backNavigateTo: ()->Unit= {}){
    val navigationIcon: (@Composable ()->Unit)?=
        if (!title.contains("Wishlist")){
                {
            IconButton(onClick = { backNavigateTo() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            }
            }else{
                null
            }


        TopAppBar(title = { Text(text = title,modifier = Modifier.padding(top= 16.dp), fontSize = 26.sp) }, backgroundColor =
        Color(red = 100, green = 120, blue = 200),
            elevation = 3.dp,
            navigationIcon = navigationIcon ,

            )


}