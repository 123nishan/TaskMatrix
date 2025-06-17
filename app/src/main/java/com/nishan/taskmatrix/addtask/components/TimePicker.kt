package com.nishan.taskmatrix.addtask.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.tooling.preview.Preview
import com.nishan.taskmatrix.addtask.util.AddTaskEvents
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

@Composable
fun TimeSelector(
    modifier: Modifier = Modifier,
    onStartTimeSelectedChange: () -> Unit,
    onEndTimeSelectedChange: () -> Unit,
    isStartTimeSelected: Boolean,
    isEndTimeSelected: Boolean,
    selectedStartTime: String,
    selectedEndTime: String,
    isAllDaySelected: Boolean

) {
    AnimatedVisibility(!isAllDaySelected) {
        Column(
            modifier = modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Start Time",
                    style = MaterialTheme.typography.titleMedium,
                )
                FilterChip(
                    onClick = onStartTimeSelectedChange,
                    label = {
                        Text(
                            selectedStartTime,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    selected = isStartTimeSelected
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "End Time",
                    style = MaterialTheme.typography.titleMedium,
                )
                FilterChip(
                    onClick = onEndTimeSelectedChange,
                    label = {
                        Text(
                            selectedEndTime,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    selected = isEndTimeSelected
                )
            }
        }
    }
}

@Preview
@Composable
private fun TimeSelectorPreview() {
    TaskMatrixTheme {
        TimeSelector(
            onStartTimeSelectedChange = {},
            onEndTimeSelectedChange = {},
            isStartTimeSelected = true,
            isEndTimeSelected = false,
            selectedStartTime = "10:00",
            selectedEndTime = "12:00",
            isAllDaySelected = false

        )
    }
}