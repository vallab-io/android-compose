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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Scaffold(
        modifier = modifier
            .fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "NEXTSTEP Repositories")
                }
            )
        }

    ) { innerPadding ->
        when (val state = uiState) {
            GithubUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            GithubUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.text_empty_list),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            is GithubUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    items(state.repositories) { item ->
                        GithubItem(repositoryEntity = item)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScreenContent(
    uiState: GithubUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "NEXTSTEP Repositories") },
            )
        },
    ) { innerPadding ->
        when (val state = uiState) {
            GithubUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            GithubUiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.text_empty_list),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            is GithubUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    items(state.repositories) { item ->
                        GithubItem(repositoryEntity = item)
                    }
                }
            }
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
                    RepositoryEntity(
                        fullName = "홍길동",
                        description = "홍길동 입니다.",
                    ),
                    RepositoryEntity(
                        fullName = "김철수",
                        description = "김철수 입니다김철수 입니다김철수 입니다김철수 입니다김철수 입니다",
                    )
                )
            )
        )
    }
}


