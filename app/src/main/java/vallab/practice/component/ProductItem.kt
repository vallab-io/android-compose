package vallab.practice.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vallab.practice.R
import vallab.practice.model.CartItem
import vallab.practice.model.Product
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.PracticeTheme


@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    cartItem: CartItem? = null,
    onCountPlus: () -> Unit = {},
    onCountMinus: () -> Unit = {},
    onClick: () -> Unit,
) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        Box(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 2.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 6.dp),
                contentScale = ContentScale.Crop,
            )

            if (cartItem == null) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(50.dp)
                        .align(Alignment.BottomEnd),
                    shape = CircleShape,
                    containerColor = Color.White,
                    onClick = onCountPlus,
                ) {
                    Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.count_fab), tint = Color.Black)
                }
            } else {
                CountComponent(
                    cartItem = cartItem,
                    onCountPlus = onCountPlus,
                    onCountMinus = onCountMinus,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 6.dp),
                )
            }
        }

        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )
            Text(
                text = stringResource(R.string.price_format).format(product.price),
                lineHeight = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    PracticeTheme {
        ProductItem(product = dummyProducts[0], onClick = {})
    }
}


@Preview(showBackground = true, name = "긴 상품명")
@Composable
private fun ProductItemPreview_LongName() {
    PracticeTheme {
        ProductItem(
            onClick = {},
            product = Product(
                id = 1,
                name = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                price = 12000,
                imageUrl = "",
            )
        )
    }
}

@Preview(showBackground = true, name = "큰 금액 포맷확인")
@Composable
private fun ProductItemPreview_LargePrice() {
    PracticeTheme {
        ProductItem(
            onClick = {},
            product = Product(
                id = 1,
                name = "상품명",
                price = 123456789,
                imageUrl = ""
            )
        )
    }
}

@Preview(showBackground = true, name = "가격 0원")
@Composable
private fun ProductItemPreview_ZeroPrice() {
    PracticeTheme {
        ProductItem(
            onClick = {},
            product = Product(
                id = 1,
                name = "상품명",
                price = 0,
                imageUrl = ""
            )
        )
    }
}

@Preview(showBackground = true, name = "수량조절창 표시")
@Composable
private fun ProductItemPreview_Show_Count() {
    PracticeTheme {
        ProductItem(
            product = dummyProducts[0],
            cartItem = CartItem(dummyProducts[0], count = 99),
            onClick = {},
        )
    }
}
