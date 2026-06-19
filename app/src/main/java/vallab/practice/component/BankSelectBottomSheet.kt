package vallab.practice.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import vallab.practice.model.BankType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankSelected: (BankType) -> Unit,
    onDismiss: () -> Unit
) {

    var selectedBank by remember {
        mutableStateOf(BankType.NOT_SELECTED)
    }
    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != BankType.NOT_SELECTED) {
            onBankSelected(selectedBank)
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
    ) {
        BankSelectRow(onClick = { selectedBank = it })
    }
}