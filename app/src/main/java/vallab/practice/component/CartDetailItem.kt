package vallab.practice.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vallab.practice.R
import vallab.practice.model.CartItem
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.PracticeTheme


@Composable
fun CartDetailItem(
    modifier: Modifier = Modifier,
    cartItem: CartItem,
    onCountPlus: () -> Unit = {},
    onCountMinus: () -> Unit = {},
    onRemoveAll: () -> Unit = {},
) {
    OutlinedCard(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Gray),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = cartItem.product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = onRemoveAll) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.product_cancel)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    model = cartItem.product.imageUrl,
                    contentDescription = cartItem.product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.price_format).format(cartItem.product.price),
                        fontSize = 16.sp
                    )

                    CountComponent(
                        count = cartItem.count,
                        onCountPlus = onCountPlus,
                        onCountMinus = onCountMinus
                    )
                }
            }
        }
    }
}

class CartItemPreviewParameterProvider : PreviewParameterProvider<CartItem> {
    override val values = sequenceOf(
        CartItem(dummyProducts[0], count = 1),
        CartItem(dummyProducts[1], count = 2),
        CartItem(
            product = dummyProducts[0].copy(
                name = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하",
                price = 12000,
            ),
            count = 9999999,
        ),
        CartItem(dummyProducts[0].copy(price = 123456789), count = 3),
    )
}


@Preview(showBackground = true)
@Composable
private fun CartDetailItemPreview(
    @PreviewParameter(CartItemPreviewParameterProvider::class) cartItem: CartItem,
) {
    PracticeTheme {
        CartDetailItem(
            cartItem = cartItem,
            onCountPlus = {},
            onCountMinus = {},
            onRemoveAll = {},
            modifier = Modifier.padding(12.dp),
        )
    }
}