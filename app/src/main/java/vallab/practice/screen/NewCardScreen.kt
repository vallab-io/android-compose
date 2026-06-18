package vallab.practice.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vallab.practice.R
import vallab.practice.component.BankSelectBottomSheet
import vallab.practice.component.CardNumberVisualTransformation
import vallab.practice.component.ExpiryDateVisualTransformation
import vallab.practice.component.NewCardTopBar
import vallab.practice.component.PaymentCard
import vallab.practice.model.BankType
import vallab.practice.ui.theme.PracticeTheme
import vallab.practice.validation.CardValidation
import vallab.practice.validation.OwnerNameValidation


@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: NewCardViewModel
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    val bankType by viewModel.bankType.collectAsStateWithLifecycle()


    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    val cardValidation = remember { CardValidation() }
    val ownerNameError = cardValidation.validateOwnerName(ownerName)


    if (bankType == BankType.NOT_SELECTED) {
        BankSelectBottomSheet(
            onBankSelected = { viewModel.setBankType(it) },
        )
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = { viewModel.addCard() })
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(bankType = bankType)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = viewModel::setCardNumber,
                visualTransformation = CardNumberVisualTransformation,
                label = { Text(stringResource(R.string.text_card_number)) },
                placeholder = { Text(stringResource(R.string.placeholder_card)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = viewModel::setExpiredDate,
                visualTransformation = ExpiryDateVisualTransformation,
                label = { Text(stringResource(R.string.text_expiration)) },
                placeholder = { Text(stringResource(R.string.placeholder_card_expiration_date)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = viewModel::setOwnerName,
                label = { Text(stringResource(R.string.text_card_user_name)) },
                isError = ownerNameError != null,
                supportingText = {
                    ownerNameError?.let { message ->
                        Text(ownerNameErrorMessage(message))
                    }
                },
                placeholder = { Text(stringResource(R.string.placeholder_card_user_name)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = viewModel::setPassword,
                label = { Text(stringResource(R.string.text_password)) },
                placeholder = { Text(stringResource(R.string.placeholder_password)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Composable
private fun ownerNameErrorMessage(message: OwnerNameValidation): String = when (message) {
    OwnerNameValidation.INVALID_LENGTH -> stringResource(R.string.owner_name_length_error)
}


@Preview
@Composable
private fun NewCardScreen_Preview() {
    PracticeTheme {
        NewCardScreen(
            viewModel = remember { NewCardViewModel() },
            navigateToCardList = {},
            onBackClick = {}
        )
    }
}

@Preview(name = "카드번호/만료일 입력")
@Composable
private fun NewCardScreen_Preview_Separator() {
    PracticeTheme {
        val viewModel = remember {
            NewCardViewModel().apply {
                setCardNumber("1234567812345678")
                setExpiredDate("0123")
            }
        }
        NewCardScreen(
            viewModel = viewModel,
            navigateToCardList = {},
            onBackClick = {})
    }
}

@Preview(name = "이름이 30자 넘어갔을 때")
@Composable
private fun NewCardScreen_Preview_OwnerNameError() {
    PracticeTheme {
        val viewModel = remember {
            NewCardViewModel().apply {
                setOwnerName("김".repeat(31))
            }
        }
        PracticeTheme {
            NewCardScreen(
                viewModel = viewModel,
                navigateToCardList = {},
                onBackClick = {})
        }
    }
}

@Preview
@Composable
private fun NewCardScreen_Preview_selectedCard() {
    PracticeTheme {
        val viewModel = remember {
            NewCardViewModel().apply { setBankType(BankType.KAKAO) }
        }

        NewCardScreen(
            viewModel = viewModel,
            navigateToCardList = {},
            onBackClick = {}
        )
    }
}