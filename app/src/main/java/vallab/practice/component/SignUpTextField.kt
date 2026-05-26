package vallab.practice.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    label: String
) {
    TextField(
        value = value, onValueChange = onValueChange, label = { Text(label) },
        isError = isError,
        supportingText = errorMessage?.let {errorMessage ->
            if (isError) {
                { Text(text = errorMessage) }
            } else null
        },
        modifier = modifier
            .fillMaxWidth()
    )
}