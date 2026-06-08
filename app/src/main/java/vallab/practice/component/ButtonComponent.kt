package vallab.practice.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.sp
import vallab.practice.ui.theme.Blue100

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
                modifier = modifier
                . fillMaxWidth ()
            .navigationBarsPadding(),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue100,
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            fontSize = 22.sp
        )
    }
}