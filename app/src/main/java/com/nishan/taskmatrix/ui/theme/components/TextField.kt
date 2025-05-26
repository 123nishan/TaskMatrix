package com.nishan.taskmatrix.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
    label: String = "Default",
    minLines: Int = 1,

) {

    OutlinedTextField(
        state = state,
        label = {
            Text(label)
        },
        leadingIcon = leadingIcon,
        lineLimits = TextFieldLineLimits.MultiLine(minLines,maxLines),
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Apply rounded corners here
    )
}


@Preview(
    showBackground = true
)
@Composable
private fun TaskMatrixTextFieldPreview() {
    TaskMatrixTheme {
        TaskMatrixTextField(state = rememberTextFieldState(initialText = "Search Tasks..."),
            modifier = Modifier.fillMaxWidth(),
            minLines = 15,
            leadingIcon = {
                Icon(Icons.Default.Search,null)
            })
    }
}