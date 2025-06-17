package com.nishan.taskmatrix.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import com.nishan.taskmatrix.ui.mapper.toUi
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme


@Composable
fun MatrixQuadrantRoot(modifier: Modifier = Modifier) {

}
//TODO update MatrixQuadrant to UIQuadrant later
@Composable
fun MatrixQuadrantScreen(quadrantCount: Map<MatrixQuadrant, Int>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            MatrixCard(
                matrixQuadrant = MatrixQuadrant.DoFirst.toUi(quadrantCount[MatrixQuadrant.DoFirst] ?: 0),
                modifier = Modifier.weight(1f)
            )
            MatrixCard(
                matrixQuadrant = MatrixQuadrant.Schedule.toUi(quadrantCount[MatrixQuadrant.Schedule] ?: 0),
                modifier = Modifier.weight(1f)
            )

        }
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            MatrixCard(
                matrixQuadrant = MatrixQuadrant.Delegate.toUi(quadrantCount[MatrixQuadrant.Delegate] ?: 0),
                modifier = Modifier.weight(1f)

            )
            MatrixCard(
                matrixQuadrant = MatrixQuadrant.Eliminate.toUi(quadrantCount[MatrixQuadrant.Eliminate] ?: 0),
                modifier = Modifier.weight(1f)

            )
        }

    }
}

@PreviewScreenSizes(
)
@Preview
@Composable
private fun MatrixQuadrantPreview() {
    TaskMatrixTheme {
        MatrixQuadrantScreen(
            modifier = Modifier.safeDrawingPadding(), quadrantCount = mapOf(
                MatrixQuadrant.DoFirst to 10,
                MatrixQuadrant.Delegate to 10,
                MatrixQuadrant.Eliminate to 10,
                MatrixQuadrant.Schedule to 10
            )
        )

    }
}