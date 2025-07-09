package com.nishan.taskmatrix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nishan.taskmatrix.addtask.navigation.addTaskScreen
import com.nishan.taskmatrix.addtask.navigation.navigateToAddTask
import com.nishan.taskmatrix.home.navigation.homeScreen

@Composable
fun TaskNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController  = rememberNavController(),
    onShowSnackbar: suspend (String, String? ) -> Boolean
){
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        homeScreen(onAddTaskClick = navController::navigateToAddTask)
        addTaskScreen(onShowSnackbar)
    }
}