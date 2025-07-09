package com.nishan.taskmatrix.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nishan.taskmatrix.home.HomeScreenRoot
import com.nishan.taskmatrix.navigation.Home

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(Home)

fun NavGraphBuilder.homeScreen(onAddTaskClick: () -> Unit){
   composable<Home> {
       HomeScreenRoot(onAddTaskClick = onAddTaskClick)
   }
}