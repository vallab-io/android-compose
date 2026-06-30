package vallab.practice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vallab.practice.App
import vallab.practice.data.repository.GithubRepository

class GithubViewModel(
    val githubRepository: GithubRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GithubUiState>(GithubUiState.Loading)
    val uiState: StateFlow<GithubUiState> = _uiState.asStateFlow()

    init {
        getRepositories()
    }


    private fun getRepositories() {
        viewModelScope.launch {
            _uiState.value = GithubUiState.Loading
            val repositories = githubRepository.getRepositories("next-step")

            if (repositories.isEmpty()) {
                _uiState.value = GithubUiState.Empty
            } else {
                _uiState.value = GithubUiState.Success(repositories)
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as App)
                    .appContainer
                    .githubRepository
                GithubViewModel(githubRepository)
            }
        }
    }
}