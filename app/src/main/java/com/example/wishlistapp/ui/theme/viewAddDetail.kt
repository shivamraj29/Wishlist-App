package com.example.wishlistapp.ui.theme

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun viewAddDetail(id:Long,
                  viewModel: MainViewModel,
                  navController: NavController){

    val scaffoldState = rememberScaffoldState()
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    if(id!=0L){
        val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0,"",""))
        viewModel.titileInput = wish.value.title
        viewModel.descInput = wish.value.description
    }else{
        viewModel.titileInput= ""
        viewModel.descInput= ""
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(title = if (id==0L){
            "Add Wish"
        }else{
            "Edit Wish"
        }, {navController.navigateUp()})},
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .fillMaxWidth()) {


                Column (modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                Spacer(modifier = Modifier.padding(16.dp))

                OutlinedTextField(value = viewModel.titileInput, onValueChange = { viewModel.onTitleChange(it) },
                    label = { Text(text = "Enter Title") }, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(value = viewModel.descInput, onValueChange = { viewModel.onDescChange(it) },
                    label = { Text(text = "Enter Description") })

                    Spacer(modifier = Modifier.padding(12.dp))
                Button(onClick = {
                    if(viewModel.descInput.isNotEmpty()&&
                        viewModel.titileInput.isNotEmpty()){
                        if (id==0L){
                            viewModel.addWish(
                                Wish(
                                    title = viewModel.titileInput.trim(),
                                    description = viewModel.descInput.trim()
                                )
                            )
                            snackMessage.value= "Wish added"
                        }else{
                            viewModel.updateWish(Wish(
                                id = id,
                                title = viewModel.titileInput.trim(),
                                description = viewModel.descInput.trim()
                            ))

                        }

                    }else{
                        snackMessage.value = "Enter field details"
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }

                }) {
                    Text(text = if (id==0L){
                    "Add Wish"
                }else{
                    "Edit Wish"
                })

                }

            }
        }
    }

}