package com.nishan.taskmatrix.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nishan.taskmatrix.domain.model.Priority
import com.nishan.taskmatrix.home.components.MatrixQuadrantScreen
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme
import com.nishan.taskmatrix.ui.theme.components.TaskCard
import com.nishan.taskmatrix.ui.theme.components.TaskMatrixTextField
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    onAddTaskClick: () -> Unit,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
    HomeScreen(onAddTaskClick = onAddTaskClick, feedState = feedState)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onAddTaskClick: () -> Unit, feedState: HomeUiState) {

    when (feedState) {
        is HomeUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is HomeUiState.Success -> {
            //TODO test this perfomance 
//        val taskFeed  =  remember(feedState.feed) {
//            feedState.feed.map {
//                task ->
//                Triple(
//                    task,
//                    isToday(task.date, task.isAllDay),
//                    task.startTime?.let { convertEpochToTime(it) }
//                )
//            }
//        }
            Box {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    item {
                        Text(
                            text = "My tasks",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    item {
                        TaskMatrixTextField(
                            state = rememberTextFieldState(), //TODO: update the state in upper composable
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = "Search Task"
                        )
                    }

                    item {
                        MatrixQuadrantScreen(
                            quadrantCount = feedState.quadrantCount
                        )
                    }
                    item {
                        Text(
                            text = "Today's Timeline",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLargeEmphasized
                        )
                    }

                    item {
                        Text(
                            text = "Today",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLargeEmphasized
                        )
                    }


                    items(
                        items = feedState.feed,
                    ) { task ->
                        val isToday = isToday(
                            date = task.date,
                            isAllDay = task.isAllDay
                        )
                        val startTime = task.startTime?.let { convertEpochToTime(it) }
                        TaskCard(
                            modifier = Modifier,
                            priority = Priority.Medium,
                            taskName = task.title,
                            due = if (isToday) "Today" else "$startTime",//TODO fix
                            checked = false,
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                    }

                }
                FloatingActionButton(
                    onClick = { onAddTaskClick() },
                    modifier = Modifier
                        .padding(bottom = 16.dp, end = 16.dp)
                        .align(Alignment.BottomEnd)

                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        }

        is HomeUiState.LoadFailed -> {

        }
    }
}

fun convertEpochToTime(timeEpoch: Long?): String {
    val dateTime = Instant.ofEpochMilli(timeEpoch!!)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    return "${dateTime.hour}:${dateTime.minute}"
}

fun isToday(date: Long?, isAllDay: Boolean): Boolean {
    if (isAllDay) {
        date?.let {
            val dateTime = Instant.ofEpochMilli(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()
            return@isToday dateTime == today

        }
        return false
    } else {
        return false
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_7_PRO
)
@Composable
private fun HomeScreenPreview() {
    TaskMatrixTheme {
        HomeScreen(onAddTaskClick = {}, feedState = HomeUiState.Loading)
    }
}