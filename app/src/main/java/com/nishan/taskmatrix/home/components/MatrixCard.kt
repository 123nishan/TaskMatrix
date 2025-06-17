package com.nishan.taskmatrix.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import com.nishan.taskmatrix.ui.mapper.UiQuadrant
import com.nishan.taskmatrix.ui.mapper.toUi
import com.nishan.taskmatrix.ui.theme.TaskMatrixTheme


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MatrixCard(
    modifier: Modifier = Modifier,
    matrixQuadrant: UiQuadrant = MatrixQuadrant.DoFirst.toUi(10),
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = matrixQuadrant.color
        ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = matrixQuadrant.icon,
                contentDescription = matrixQuadrant.title,
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
            )
            Text(
                matrixQuadrant.title,
                style = MaterialTheme.typography.titleLargeEmphasized.copy(color = Color.White),
            )
            Text(
                text = matrixQuadrant.taskCount.toString(),
                style = MaterialTheme.typography.headlineSmallEmphasized.copy(color = Color.White)
            )
        }

    }
}

@Preview
@Composable
private fun MatrixCardPreview() {
    TaskMatrixTheme {
        MatrixCard(
            matrixQuadrant = MatrixQuadrant.DoFirst.toUi(10),
        )
    }
}