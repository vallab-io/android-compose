package vallab.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vallab.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                SignUpView()
            }
        }
    }
}

@Composable
fun SignUpView() {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }

    val passwordLength = password.isNotEmpty() && password.length !in 8..16

    val passWordError = passwordConfirm.isNotEmpty() && password != passwordConfirm

    Column(
        modifier = Modifier
            .padding(top = 112.dp, start = 32.dp, end = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Welcome to Compose \uD83D\uDE80",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        SignUpTextField(
            modifier = Modifier.padding(top = 36.dp),
            value = userName,
            label = "UserName",
            onValueChange = { userName = it },
        )


        SignUpTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = email,
            label = "email",
            onValueChange = { email = it },
        )



        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = password,
            label = "Password",
            onValueChange = { password = it },
            isError = passwordLength,
            errorMessage = "비밀번호는 8~16자여야 합니다"
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 30.dp),
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = "Password Confirm",
            isError = passWordError,
            errorMessage = "비밀번호가 일치하지 않습니다"

        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(text = "sign up")
        }
    }
}

@Composable
fun SignUpTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value, onValueChange = onValueChange, label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun PasswordTextField(
    modifier: Modifier,
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeTheme {
        SignUpView()
    }
}
