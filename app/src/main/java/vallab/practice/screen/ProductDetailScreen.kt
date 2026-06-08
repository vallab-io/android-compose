package vallab.practice.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vallab.practice.CartDetailActivity
import vallab.practice.R
import vallab.practice.component.ButtonComponent
import vallab.practice.model.Cart
import vallab.practice.model.Product
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.PracticeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    product: Product
) {
    val context = LocalContext.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.title_product_detail)) },
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
            ButtonComponent(
                text = stringResource(R.string.add_to_cart),
                onClick = {
                    Cart.addOne(product)
                    context.startActivity(
                        Intent(context, CartDetailActivity::class.java)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(
                text = product.name,
                modifier = Modifier
                    .padding(15.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black)

            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.text_price), fontSize = 22.sp)
                Text(
                    text = stringResource(R.string.price_format).format(product.price),
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductDetailScreenPreview() {
    PracticeTheme {
        ProductDetailScreen(
            product = dummyProducts[0],
            onBackButtonClick = {})
    }
}

@Preview(showBackground = true, name = "길고 긴 이름")
@Composable
private fun ProductDetailScreenPreview_LongName() {
    PracticeTheme {
        ProductDetailScreen(
            product = dummyProducts[0].copy(name = "가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하"),
            onBackButtonClick = {},
        )
    }
}

@Preview(showBackground = true, name = "큰 금액")
@Composable
private fun ProductDetailScreenPreview_LargePrice() {
    PracticeTheme {
        ProductDetailScreen(
            product = dummyProducts[0].copy(price = 123456789),
            onBackButtonClick = {},
        )
    }
}
