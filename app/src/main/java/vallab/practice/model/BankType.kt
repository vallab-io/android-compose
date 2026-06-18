package vallab.practice.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import vallab.practice.R

enum class BankType(
    val cardName: String = "",
    val cardColor: Color = Color(0xFFf04651),
    @param:DrawableRes val iconRes: Int = 0,
) {
    NOT_SELECTED,

    BC("BC카드", Color(0xFFf04651), R.drawable.bc),
    SHINHAN("신한카드", Color(0xFF0046ff), R.drawable.shinhan),
    KAKAO("카카오뱅크", Color(0xFFffe600), R.drawable.kakao),
    HYUNDAI("현대카드", Color(0xFF000000), R.drawable.hyundai),
    WOORI("우리카드", Color(0xFF007cc9), R.drawable.woori),
    LOTTE("롯데카드", Color(0xFFed1c24), R.drawable.lotte),
    HANA("하나카드", Color(0xFF009490), R.drawable.hana),
    KB("국민카드", Color(0xFF696055), R.drawable.kb),
}