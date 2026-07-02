package vallab.practice.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import vallab.practice.R
import vallab.practice.data.model.RepositoryEntity
import vallab.practice.ui.theme.PracticeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    LaunchedEffect(uiState) {
        if (uiState is GithubUiState.Error) {
            when (snackbarHostState.showSnackbar(
                message = context.getString(R.string.text_error_massage),
                actionLabel = context.getString(R.string.text_retry)
            )) {
                SnackbarResult.ActionPerformed -> viewModel.retry()
                SnackbarResult.Dismissed -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "NEXTSTEP Repositories")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }

    ) { innerPadding ->
        GithubScreenContent(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScreenContent(
    uiState: GithubUiState,
    modifier: Modifier = Modifier,
) {

    when (uiState) {
        GithubUiState.Loading -> LoadingContent()

        GithubUiState.Empty -> EmptyContent()

        is GithubUiState.Success -> SuccessContent(repositories = uiState.repositories)
        GithubUiState.Error -> {}
    }


}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag("Indicator_Loading")
        )
    }
}

@Composable
private fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.text_empty_list),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    repositories: List<Repository>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(repositories) { item ->
            GithubItem(repository = item)
        }
    }
}


@Preview(showBackground = true, name = "Loading")
@Composable
private fun GithubScreen_Preview_Loading() {
    PracticeTheme {
        GithubScreenContent(uiState = GithubUiState.Loading)
    }
}

@Preview(showBackground = true, name = "Empty")
@Composable
private fun GithubScreen_Preview_Empty() {
    PracticeTheme {
        GithubScreenContent(uiState = GithubUiState.Empty)
    }
}

@Preview(showBackground = true, name = "Success")
@Composable
private fun GithubScreen_Preview_Success() {
    PracticeTheme {
        GithubScreenContent(
            uiState = GithubUiState.Success(
                repositories = listOf(
                    Repository(
                        fullName = "홍길동",
                        description = "홍길동 입니다.",
                        stars = 120
                    ),
                    Repository(
                        fullName = "김철수",
                        description = "김철수 입니다김철수 입니다김철수 입니다김철수 입니다김철수 입니다",
                        stars = 10
                    )
                )
            )
        )
    }
}

