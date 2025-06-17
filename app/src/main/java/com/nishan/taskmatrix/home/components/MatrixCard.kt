package com.nishan.taskmatrix.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.domain.model.Task
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

@Composable
fun PriorityCard(modifier: Modifier = Modifier,

                 ) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxSize()
    ) {

    }
}

@Preview
@Composable
private fun PriorityCardPreview() {
    TaskMatrixTheme {
        PriorityCard()
    }
}