package vallab.practice.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vallab.practice.R
import vallab.practice.component.AddCardButton
import vallab.practice.component.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsScreen(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit,
    viewModel: PaymentsViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.title_payments)) },
                actions = {
                    if (uiState is CreditCardUiState.Many) {
                        Text(
                            text = stringResource(R.string.add),
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable(onClick = onAddCardClick)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val state = uiState) {
                CreditCardUiState.Empty -> {
                    Text(
                        text = stringResource(R.string.register_new_card),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        textAlign = TextAlign.Center
                    )
                    AddCardButton(
                        onClick = onAddCardClick
                    )
                }

                is CreditCardUiState.One -> {
                    PaymentCard(
                        card = state.card,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                    AddCardButton(
                        onClick = onAddCardClick
                    )
                }

                is CreditCardUiState.Many -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        items(state.cards) { card ->
                            PaymentCard(card = card)
                        }
                    }
                }
            }
        }

    }

}

//@Preview
//@Composable
//private fun PaymentScreen_Preview() {
//    PracticeTheme {
//        val viewModel = remember { PaymentsViewModel() }
//        val uiState by viewModel.uiState.collectAsState()
//        PaymentsScreen(
//            viewModel = viewModel,
//            onAddCardClick = {}
//        )
//    }
//}