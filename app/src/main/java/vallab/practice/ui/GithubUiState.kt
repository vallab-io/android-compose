package vallab.practice.ui

import vallab.practice.data.model.RepositoryEntity

sealed interface GithubUiState {
    data object Loading : GithubUiState
    data object Empty : GithubUiState
    data class Success(val repositories: List<RepositoryEntity>) : GithubUiState
}
