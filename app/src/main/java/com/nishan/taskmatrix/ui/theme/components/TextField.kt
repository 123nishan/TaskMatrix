package com.nishan.taskmatrix.ui.theme.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

@Composable
fun TaskMatrixTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholderText: String = "Default"
) {

    TextField(
        state = state,
        placeholder = {
            Text("")
        },
        leadingIcon = leadingIcon,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Apply rounded corners here
    )
}


@Preview
@Composable
private fun TaskMatrixTextFieldPreview() {
    TaskMatrixTheme {
        TaskMatrixTextField(state = rememberTextFieldState(initialText = "Search Tasks...")
        ,
            leadingIcon = {
                Icon(Icons.Default.Search,null)
            })
    }
}