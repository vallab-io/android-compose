package vallab.practice

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import vallab.practice.data.model.RepositoryEntity
import vallab.practice.data.repository.GithubRepository
import vallab.practice.ui.GithubScreen
import vallab.practice.ui.GithubScreenContent
import vallab.practice.ui.GithubUiState
import vallab.practice.ui.GithubViewModel
import vallab.practice.ui.theme.PracticeTheme

class GithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `로딩_중_일_때_ProgressIndicator가_노출되어야_한다`() {
        composeTestRule.setContent {
            PracticeTheme {
                GithubScreenContent(uiState = GithubUiState.Loading)
            }
        }
        composeTestRule
            .onNodeWithTag("Indicator_Loading")
            .assertIsDisplayed()
    }


    @Test
    fun `목록이_비었을_때_빈_목록_문구가_노출되어야_한다`() {
        composeTestRule.setContent {
            PracticeTheme {
                GithubScreenContent(uiState = GithubUiState.Empty)
            }
        }
        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `에러_상태일_때_스낵바와_재시도_버튼이_노출되어야_한다`() {
        val testRepository = object : GithubRepository {
            override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
                throw Exception()
            }
        }

        val testViewModel = GithubViewModel(testRepository)

        composeTestRule.setContent {
            PracticeTheme {
                GithubScreen(
                    viewModel = testViewModel
                )
            }
        }
        composeTestRule
            .onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("재시도")
            .performClick()
    }
}
