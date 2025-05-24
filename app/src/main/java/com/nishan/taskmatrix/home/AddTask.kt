package com.nishan.taskmatrix.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isAllDaySelected by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = TextFieldDefaults.MinHeight),
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .clickable {
                       if(!isExpanded) {
                           isExpanded = true
                       }
                    }
            ) {

                Column {
                    ExpandableSectionTitle(
                        isExpanded = isExpanded,
                        title = "Time",
                        closeExpanded = {isExpanded = false}
                    )
                    AnimatedVisibility(
                        modifier = Modifier
                            .fillMaxWidth(),
                        visible = isExpanded
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            AllDayRow(allDaySelected = isAllDaySelected, onAllDaySelectedChange = {
                                isAllDaySelected = it
                            })
                            DateAndTimeSelector(isAllDaySelected = isAllDaySelected)
                        }

                    }
                }
            }
        }


    }
}

@Composable
fun DateAndTimeSelector(modifier: Modifier = Modifier, isAllDaySelected: Boolean) {
    var isDateSelected by remember { mutableStateOf(false) }
    var isTimeSelected by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        FilterChip(
            onClick = {
                isDateSelected = !isDateSelected
            },
            label = {
                Text(
                    "Thus, 17 Apr",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            selected = isDateSelected
        )
        Spacer(modifier = Modifier.size(16.dp))
        VerticalDivider(
            color = Color.Black,
            modifier = Modifier.fillMaxHeight().width(1.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        AnimatedVisibility  (!isAllDaySelected) {
            FilterChip(
                onClick = {
                    isTimeSelected = !isTimeSelected
                },
                label = {
                    Text(
                        "00:00",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                selected = isTimeSelected
            )
        }


    }
}

@Composable
fun AllDayRow(modifier: Modifier = Modifier, allDaySelected: Boolean, onAllDaySelectedChange: (Boolean) -> Unit) {

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccessTime,
            contentDescription = null,
            modifier = Modifier
                .align(
                    Alignment.CenterVertically
                )
        )
        Text(
            text = "All Day",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = allDaySelected,
            onCheckedChange = onAllDaySelectedChange
        )


    }
}

@Composable
fun ExpandableSectionTitle(modifier: Modifier = Modifier, isExpanded: Boolean, title: String, closeExpanded: () -> Unit) {
    val icon =
        if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.CalendarMonth,
            contentDescription = null,
            modifier = Modifier
                .align(
                    Alignment.CenterVertically
                )
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        if (isExpanded) {
            IconButton(
                onClick = closeExpanded
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = null,

                    )
            }
        }
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