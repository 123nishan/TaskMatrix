package com.nishan.taskmatrix.addtask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nishan.taskmatrix.addtask.AddTaskRoot
import com.nishan.taskmatrix.navigation.AddTask

fun NavController.navigateToAddTask(navOption: NavOptions? = null) = navigate(route = AddTask)

fun NavGraphBuilder.addTaskScreen(onShowSnackbar: suspend (String, String?) -> Boolean) {
    composable<AddTask> {
        AddTaskRoot(
            onShowSnackbar = onShowSnackbar
        )
    }
}