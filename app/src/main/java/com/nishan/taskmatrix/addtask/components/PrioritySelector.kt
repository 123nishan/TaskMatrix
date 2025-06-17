package com.nishan.taskmatrix.addtask.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults.filterChipColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.nishan.taskmatrix.domain.model.Priority
import com.nishan.taskmatrix.ui.mapper.toUi
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme

@Composable
fun PrioritySelector(
    modifier: Modifier = Modifier,
    selectedPriority: Priority,
    onPrioritySelectionChange: (Priority) -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        maxLines = 2
    ) {
        Priority.values.forEach {
            val ui = it.toUi()
            FilterChip(
                modifier = Modifier.weight(1f),
                selected = it == selectedPriority,
                onClick = {
                    onPrioritySelectionChange(it)
                },
                label = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            for (i in 1..ui.level) {
                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = Color("#fcad03".toColorInt()),
//                        modifier = Modifier
//                            .padding(start = if (i == 1) 14.dp else 0.dp)
                                )
                            }
                        }
                        Text(ui.title)
                    }
                },
                colors = filterChipColors(containerColor = ui.color),

                )
        }
    }
}


@Preview(
    showBackground = true,
    device = PIXEL_7_PRO
)
@Composable
private fun PrioritySelectorPreview() {
    TaskMatrixTheme {
        PrioritySelector(
            selectedPriority = Priority.Low,
            onPrioritySelectionChange = {}

        )
    }
}