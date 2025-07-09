package com.nishan.taskmatrix.addtask.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.trace

@Composable
fun DateSelector(
    selectedDate: String = "",
    isDateSelected: Boolean,
    onDateSelectedChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    trace("DateSelectorTrace") {

        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.CenterVertically
                    )
            )
            Text(
                text = "Date",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            FilterChip(
                onClick = onDateSelectedChange,
                label = {
                    Text(
                        selectedDate,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                selected = isDateSelected
            )

        }
    }
}