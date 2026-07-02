package vallab.practice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vallab.practice.R
import vallab.practice.domain.Repository
import vallab.practice.ui.theme.PracticeTheme

@Composable
fun GithubItem(
    modifier: Modifier = Modifier,
    repository: Repository
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {

            Column(
                modifier = Modifier.weight(1f),
            ) {
                if (repository.isHot()) {
                    Text(
                        text = "HOT",
                        color = colorResource(R.color.purple_primary),
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Text(
                    text = repository.fullName,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = repository.description,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

            Text(
                text = "★ ${repository.stars}",
                style = MaterialTheme.typography.labelLarge,
            )
        }


        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}


@Preview(showBackground = true, name = "HOT 노출 X")
@Composable
private fun GithubItem_Preview_Not_Show_HOT() {
    PracticeTheme {
        GithubItem(
            repository = Repository(
                "홍길동",
                "홍길동입니다",
                10
            )
        )
    }
}

@Preview(showBackground = true, name = "HOT 노출 O")
@Composable
private fun GithubItem_Preview_Show_HOT() {
    PracticeTheme {
        GithubItem(
            repository = Repository(
                "홍길동",
                "홍길동입니다",
                120
            )
        )
    }
}