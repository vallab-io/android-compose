package vallab.practice.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vallab.practice.ui.theme.PracticeTheme

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
        supportingText = errorMessage?.let { errorMessage ->
            if (isError) {
                { Text(text = errorMessage) }
            } else null
        },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview(name = "정상 입력", showBackground = true)
@Composable
private fun SignUpTextField_Preview() {
    PracticeTheme {
        SignUpTextField(
            value = "김김김",
            onValueChange = {},
            label = "UserName",
            isError = false,
            errorMessage = ""
        )
    }
}


@Preview(name = "사용자이름 에러", showBackground = true)
@Composable
private fun SignUpTextField_Preview_UserNameError() {
    PracticeTheme {
        SignUpTextField(
            value = "김",
            onValueChange = {},
            label = "UserName",
            isError = true,
            errorMessage = "이름은 2자 이상 5자 이하로 입력해주세요."
        )
    }
}