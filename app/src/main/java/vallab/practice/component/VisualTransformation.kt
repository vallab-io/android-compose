package vallab.practice.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NumberVisualTransformation(
    private val groupSizes: List<Int>,
    private val separator: String,
) : VisualTransformation {
    private val maxDigits = groupSizes.sum()
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(maxDigits)
        val formatted = buildFormatted(digits)
        return TransformedText(
            AnnotatedString(formatted),
            createOffsetMapping(digits.length),
        )
    }
    private fun buildFormatted(digits: String): String {
        if (digits.isEmpty()) return ""
        val parts = mutableListOf<String>()
        var index = 0
        for (size in groupSizes) {
            if (index >= digits.length) break
            parts += digits.substring(index, minOf(index + size, digits.length))
            index += size
        }
        return parts.joinToString(separator)
    }
    private fun createOffsetMapping(originalLength: Int): OffsetMapping {
        val sepLen = separator.length
        return object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val clamped = offset.coerceIn(0, originalLength)
                var transformed = 0
                var original = 0
                var groupIndex = 0
                var remaining = groupSizes.getOrElse(0) { 0 }
                while (original < clamped) {
                    if (remaining == 0 && groupIndex < groupSizes.lastIndex) {
                        transformed += sepLen
                        groupIndex++
                        remaining = groupSizes[groupIndex]
                    }
                    transformed++
                    original++
                    remaining--
                }
                return transformed
            }
            override fun transformedToOriginal(offset: Int): Int {
                var transformed = 0
                var original = 0
                var groupIndex = 0
                var remaining = groupSizes.getOrElse(0) { 0 }
                while (transformed < offset && original < originalLength) {
                    if (remaining == 0 && groupIndex < groupSizes.lastIndex) {
                        transformed += sepLen
                        if (transformed >= offset) break
                        groupIndex++
                        remaining = groupSizes[groupIndex]
                    }
                    transformed++
                    original++
                    remaining--
                }
                return original.coerceIn(0, originalLength)
            }
        }
    }
}
val CardNumberVisualTransformation = NumberVisualTransformation(
    groupSizes = listOf(4, 4, 4, 4),
    separator = " - ",
)
val ExpiryDateVisualTransformation = NumberVisualTransformation(
    groupSizes = listOf(2, 2),
    separator = " / ",
)