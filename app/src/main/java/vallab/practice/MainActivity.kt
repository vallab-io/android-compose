package vallab.practice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vallab.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as App).appContainer
        val repository = appContainer.githubRepository

        lifecycleScope.launch {
            val repository = repository.getRepositories("next-step")
            repository.forEach { repositoryItem ->
                Log.d(
                    "GithubRepository",
                    "${repositoryItem.fullName} / ${repositoryItem.description}"
                )
            }
        }


        enableEdgeToEdge()
        setContent {
            PracticeTheme {

            }
        }
    }
}
