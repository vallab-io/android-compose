package vallab.practice.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import vallab.practice.ui.theme.PracticeTheme

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    TextField(
        value = value, onValueChange = onValueChange, label = { Text(label) },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        isError = isError,
        supportingText = errorMessage?.let { errorMessage ->
            if (isError) {
                { Text(text = errorMessage) }
            } else null
        },
        modifier = modifier
            .fillMaxWidth()
    )
}


@Preview(name = "비밀번호 포맷 에러", showBackground = true)
@Composable
private fun PasswordFormatErrorPreview() {
    PracticeTheme {
        PasswordTextField(
            value = "abcdefgh",
            onValueChange = {},
            label = "Password",
            isError = true,
            errorMessage = "비밀번호는 영문과 숫자를 포함해야 합니다."
        )
    }
}