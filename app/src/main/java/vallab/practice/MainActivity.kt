package vallab.practice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import vallab.practice.screen.PaymentsScreen
import vallab.practice.screen.PaymentsViewModel
import vallab.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeTheme {
                RoutePayments(activity = this@MainActivity)
            }
        }
    }
}


@Composable
private fun RoutePayments(activity: MainActivity) {
    val viewModel: PaymentsViewModel = viewModel()
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.fetchCards()
        }
    }
    PaymentsScreen(
        viewModel = viewModel,
        onAddCardClick = {
            val intent = Intent(activity, NewCardActivity::class.java)
            launcher.launch(intent)
        },
        onCardClick = { index ->
            val intent = Intent(activity, NewCardActivity::class.java)
                .putExtra(NewCardActivity.CARD_INDEX, index)
            launcher.launch(intent)
        }
    )
}

