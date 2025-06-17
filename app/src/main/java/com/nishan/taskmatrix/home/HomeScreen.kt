package com.nishan.taskmatrix.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import com.nishan.taskmatrix.domain.model.Priority
import com.nishan.taskmatrix.home.components.MatrixQuadrantScreen
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskCard
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField
//import com.nishan.taskmatrix.util.MatrixQuadrant
//import com.nishan.taskmatrix.util.Priority

@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier) {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val taskList = listOf("Task 1", " Task 2")
    Column(
        modifier = modifier
            .fillMaxSize()
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
            state = rememberTextFieldState(), //TODO: update the state in upper composable
            modifier = Modifier
                .fillMaxWidth(),
            label = "Search Task"
        )

//        Row(
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Button(
//                onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .height(50.dp)
//                    .width(160.dp)
//                    ,
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text(text = "Priority Matrix")
//            }
//            Button(
//                onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .height(50.dp)
//                    .width(160.dp)
//                   ,
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                Text(text = "Time Blocks")
//            }
//        }

        MatrixQuadrantScreen(quadrantCount = mapOf(
            MatrixQuadrant.DoFirst to 10,
            MatrixQuadrant.Delegate to 10,
            MatrixQuadrant.Eliminate to 2,
            MatrixQuadrant.Schedule to 10
        ))

        Text(
            text = "Today",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMediumEmphasized
        )

        HorizontalDivider(
            color = Color.Black
        )
        for (task in taskList) {
            TaskCard(
                modifier = Modifier,
                priority = Priority.Medium,
                taskName = task,
                due = "2:00 PM",
                checked = false,
            )
        }


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