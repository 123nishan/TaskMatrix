package com.nishan.taskmatrix.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField

@Composable
fun AddTaskRoot(modifier: Modifier = Modifier) {

}

@Composable
fun AddTaskScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
    ) {
        Text(
            text = "New Task",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        TaskMatrixTextField(
            state = rememberTextFieldState(initialText = "Tasks Title..."), //TODO: update the state in upper composable
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = "Title",
            minLines = 1
        )

        TaskMatrixTextField(
            state = rememberTextFieldState(initialText = "Add description"), //TODO: update the state in upper composable
            modifier = Modifier
                .fillMaxWidth(),
            label = "Description",
            minLines = 5
        )
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_7_PRO
)
@Composable
private fun AddTaskScreenPreview() {
    TaskMatrixTheme {
        AddTaskScreen(

        )
    }
}