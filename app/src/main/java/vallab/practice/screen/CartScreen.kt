package vallab.practice.screen

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import vallab.practice.ProductDetailActivity
import vallab.practice.R
import vallab.practice.component.ProductItem
import vallab.practice.model.dummyProducts
import vallab.practice.ui.theme.PracticeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.product_list),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                actions =
                    {
                        IconButton(
                            onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                                contentColor = colorResource(R.color.black)
                            )
                        ) {
                            Icon(
                                Icons.Filled.ShoppingCart,
                                contentDescription = stringResource(R.string.shoppingCart_description)
                            )
                        }
                    })
        }) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding),
        ) {
            items(dummyProducts) { product ->
                ProductItem(
                    product = product,
                    onClick = {
                        val intent = Intent(context, ProductDetailActivity::class.java).apply {
                            putExtra("productId", product.id.toString())
                        }
                        context.startActivity(intent)
                    })
            }
        }
    }
}

@Composable
@Preview
fun CartScreenPreview() {
    PracticeTheme {
        CartScreen()
    }
}