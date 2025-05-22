package com.nishan.taskmatrix.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField

@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier) {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize( )
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Text(
            text = "My tasks",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        TaskMatrixTextField(
            state = rememberTextFieldState(initialText = "Search Tasks..."),
            modifier = Modifier
                .fillMaxWidth()


        )

        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Priority Matrix")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Time Blocks")
            }
        }

        Text(
            text = "Today",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMediumEmphasized
        )

        HorizontalDivider(
            color = Color.Black
        )



    }
}

@Preview(
showBackground = true,
    device = PIXEL_7_PRO
)
@Composable
private fun HomeScreenPreview() {
    TaskMatrixTheme {
        HomeScreen()
    }
}