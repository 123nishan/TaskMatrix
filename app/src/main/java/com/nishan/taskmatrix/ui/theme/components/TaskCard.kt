package com.nishan.taskmatrix.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.nishan.taskmatrix.domain.model.Priority
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme


@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    priority: Priority = Priority.Medium,
    taskName: String = "Default Task Name",
    due: String,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )

            Text(
                text = taskName,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (i in 1..priority.level) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Color("#fcad03".toColorInt()),
                    modifier = Modifier
                        .padding(start = if (i == 1) 14.dp else 0.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .width(14.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyMedium.toSpanStyle()

                    ) {
                        Text(text = "Due")
                    }

                    withStyle(
                        style = MaterialTheme.typography.bodyMedium.toSpanStyle()
                    ) {
                        Text(" : ")
                    }

                    withStyle(
                        style = MaterialTheme.typography.bodyMedium.toSpanStyle()
                    ) {
                        Text(due)
                    }
                },

                )
        }

    }

}

@Preview(
    showBackground = true,
    device = PIXEL_7_PRO
)
@Composable
private fun TaskCardPreview() {
    TaskMatrixTheme {
        TaskCard(
            modifier = Modifier,
            priority = Priority.Medium,
            taskName = "Default Task Name",
            due = "2:00 PM",
            checked = false,
            onCheckedChange = {}
        )
    }
}