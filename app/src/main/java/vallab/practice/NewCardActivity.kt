package vallab.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import vallab.practice.screen.NewCardScreen
import vallab.practice.screen.NewCardViewModel
import vallab.practice.ui.theme.PracticeTheme

class NewCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cardIndex = if (intent.hasExtra(CARD_INDEX)) {
            intent.getIntExtra(CARD_INDEX, -1)
        } else {
            null
        }
        setContent {
            PracticeTheme {
                val viewModel: NewCardViewModel = viewModel()
                LaunchedEffect(Unit) {
                    cardIndex?.let { viewModel.loadCard(it) }
                }
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

    companion object {
        const val CARD_INDEX = "card_index"
    }
}