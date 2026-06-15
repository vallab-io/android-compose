package vallab.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import vallab.practice.screen.NewCardScreen
import vallab.practice.screen.NewCardViewModel
import vallab.practice.ui.theme.PracticeTheme

class NewCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeTheme {
                val viewModel: NewCardViewModel = viewModel()
                NewCardScreen(
                    viewModel = viewModel,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    onBackClick = { finish() }
                )
            }
        }
    }
}