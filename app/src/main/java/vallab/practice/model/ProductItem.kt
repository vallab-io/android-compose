package vallab.practice.model

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vallab.practice.ui.theme.PracticeTheme


@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
        }

        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )
            Text(
                text = "%,d원".format(product.price),
                lineHeight = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    PracticeTheme {
        ProductItem(product = dummyProducts[0])
    }
}
