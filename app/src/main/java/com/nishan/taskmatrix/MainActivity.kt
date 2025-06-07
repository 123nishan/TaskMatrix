package com.nishan.taskmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nishan.taskmatrix.addtask.AddTaskRoot
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            TaskMatrixTheme {

                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost ={ SnackbarHost(snackBarHostState)} ) { innerPadding ->
                    Box(
                        modifier = Modifier    .padding(innerPadding)
                            .fillMaxSize()
                            .imePadding(),
                    ) {
                        AddTaskRoot(
                            snackBarHostState = snackBarHostState,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskMatrixTheme {
        Greeting("Android")
    }
}