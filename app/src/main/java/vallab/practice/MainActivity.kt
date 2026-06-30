package vallab.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import vallab.practice.ui.GithubScreen
import vallab.practice.ui.GithubViewModel
import vallab.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: GithubViewModel by viewModels { GithubViewModel.Factory }
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                GithubScreen(viewModel = viewModel)
            }
        }
    }
}
