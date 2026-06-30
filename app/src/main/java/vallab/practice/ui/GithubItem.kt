package vallab.practice.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vallab.practice.data.model.RepositoryEntity
import vallab.practice.ui.theme.PracticeTheme

@Composable
fun GithubItem(
    modifier: Modifier = Modifier,
    repositoryEntity: RepositoryEntity
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = repositoryEntity.fullName.orEmpty(),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = repositoryEntity.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun GithubItem_Preview() {
    PracticeTheme {
        GithubItem(
            repositoryEntity = RepositoryEntity(
                "홍길동",
                "홍길동입니다"
            )
        )
    }
}