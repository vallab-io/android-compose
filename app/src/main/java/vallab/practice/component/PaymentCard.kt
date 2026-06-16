package vallab.practice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vallab.practice.model.Card
import vallab.practice.ui.theme.PracticeTheme

@Composable
fun PaymentCard(modifier: Modifier = Modifier) {
    CardFrame(modifier = modifier)
}


@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card
) {
    CardFrame(modifier = modifier) {
        CardDetails(card)
    }
}


@Composable
fun CardFrame(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 14.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp),
        ) {
            content()
        }

    }
}

@Composable
private fun CardDetails(card: Card) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = maskCardNumber(card.cardNumber),
            color = Color.White,
            fontSize = 15.sp,
            lineHeight = 14.sp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = card.ownerName,
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                text = ExpiryDateVisualTransformation
                    .filter(AnnotatedString(card.expiredDate))
                    .text,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}


private fun maskCardNumber(cardNumber: String): String {
    val digits = cardNumber.filter { it.isDigit() }
    val parts = digits.chunked(4)
    val first = parts.getOrElse(0) { "****" }
    val second = parts.getOrElse(1) { "****" }
    return "$first - $second - **** - ****"
}


@Preview(name = "기본 빈 화면")
@Composable
private fun PaymentCard_Preview() {
    PracticeTheme {
        PaymentCard()
    }
}


@Preview(name = "카드 정보 입력")
@Composable
private fun PaymentCard_Preview_Input_Information() {
    PracticeTheme {
        PaymentCard(
            card = Card(
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234"
            )
        )
    }
}