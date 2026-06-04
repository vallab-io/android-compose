package vallab.practice.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vallab.practice.R
import vallab.practice.component.CartDetailItem
import vallab.practice.model.Cart
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.Blue100
import vallab.practice.ui.theme.PracticeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDetailScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    var cartItems by remember { mutableStateOf(Cart.items) }
    val totalPrice = cartItems.let { Cart.totalPrice }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.title_cart_)) },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = { onBackButtonClick() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_description)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue100,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.button_order, totalPrice),
                    fontSize = 22.sp
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(cartItems) { cartItem ->
                CartDetailItem(
                    cartItem = cartItem,
                    onCountPlus = {
                        cartItems = Cart.addOne(cartItem.product)
                    },
                    onCountMinus = {
                        cartItems = Cart.removeOne(cartItem.product)
                    },
                    onRemoveAll = {
                        cartItems = Cart.removeAll(cartItem.product)
                    },
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "기본 빈 화면")
@Composable
private fun CartDetailScreenPreview() {
    PracticeTheme {
        CartDetailScreen(onBackButtonClick = {})
    }
}

@Preview(showBackground = true, name = "아이템 2개")
@Composable
private fun CartDetailScreenPreview_TwoItems() {
    Cart.items.forEach { Cart.removeAll(it.product) }
    Cart.addOne(dummyProducts[0])
    Cart.addOne(dummyProducts[1])
    PracticeTheme {
        CartDetailScreen(onBackButtonClick = {})
    }
}

@Preview(showBackground = true, name = "수량 2개")
@Composable
private fun CartDetailScreenPreview_Count2() {
    Cart.items.forEach { Cart.removeAll(it.product) }
    Cart.addOne(dummyProducts[0])
    Cart.addOne(dummyProducts[0])
    PracticeTheme {
        CartDetailScreen(onBackButtonClick = {})
    }
}

@Preview(showBackground = true, name = "아주 긴 이름")
@Composable
private fun CartDetailScreenPreview_LongName() {
    Cart.items.forEach { Cart.removeAll(it.product) }
    val longNameDummyProduct =
        dummyProducts[0].copy(name = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하")
    Cart.addOne(longNameDummyProduct)
    PracticeTheme {
        CartDetailScreen(onBackButtonClick = {})
    }
}


@Preview(showBackground = true, name = "큰 금액")
@Composable
private fun CartDetailScreenPreview_LargePrice() {
    Cart.items.forEach { Cart.removeAll(it.product) }
    val largePriceDummyProduct =
        dummyProducts[0].copy(price = 123456789)
    Cart.addOne(largePriceDummyProduct)
    PracticeTheme {
        CartDetailScreen(onBackButtonClick = {})
    }
}
