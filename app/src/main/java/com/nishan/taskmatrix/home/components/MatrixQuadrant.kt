package com.nishan.taskmatrix.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

@Composable
fun PriorityBoxRoot(modifier: Modifier = Modifier) {

}

@Composable
fun PriorityBoxScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
           modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("first Row")
            Text("first row second")
        }
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Second Row")
            Text("Second row second")
        }

    }
}

@PreviewScreenSizes(
)
@Preview
@Composable
private fun PriorityBoxPreview() {
    TaskMatrixTheme {
        PriorityBoxScreen(modifier = Modifier.safeDrawingPadding())

    }
}