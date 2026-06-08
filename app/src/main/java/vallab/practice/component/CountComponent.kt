package vallab.practice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vallab.practice.R
import vallab.practice.ui.theme.PracticeTheme

@Composable
fun CountComponent(
    modifier: Modifier = Modifier,
    count: Int,
    onCountPlus: () -> Unit,
    onCountMinus: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(5.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = onCountMinus) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = stringResource(R.string.icon_minus)
            )
        }

        Text(text = count.toString(), fontSize = 20.sp)

        IconButton(onClick = onCountPlus) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.icon_plus)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountComponentPreview() {
    PracticeTheme {
        CountComponent(
            count = 3,
            onCountPlus = {},
            onCountMinus = {}
        )
    }
}
