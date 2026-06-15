package vallab.practice.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vallab.practice.R

@Composable
fun AddCardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(top = 30.dp)
            .size(width = 220.dp, height = 124.dp)
            .background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_card),
            tint = Color.Black,
        )
    }
}

@Preview
@Composable
private fun AddCartButton_Preview() {
    AddCardButton(
        onClick = {}
    )
}