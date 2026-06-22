package vallab.practice.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import vallab.practice.R
import vallab.practice.ui.theme.PracticeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    modifier: Modifier = Modifier,
    isModifying: Boolean,
    isSaveEnabled: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(if (isModifying) R.string.modify_card else R.string.add_card)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.description_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSaveClick() },
                enabled = isSaveEnabled
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.description_finish),
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "카드 수정 & 버튼 비활성")
@Composable
private fun NewCardTopBar_Preview_Modifying() {
    PracticeTheme {
        NewCardTopBar(
            isModifying = true,
            isSaveEnabled = false,
            onBackClick = {},
            onSaveClick = {}
        )
    }
}

@Preview(showBackground = true, name = "카드 추가 & 버튼 활성")
@Composable
private fun NewCardTopBar_Preview_Add() {
    PracticeTheme {
        NewCardTopBar(
            isModifying = false,
            isSaveEnabled = true,
            onBackClick = {},
            onSaveClick = {},
        )
    }
}

