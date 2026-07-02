package vallab.practice.domain

data class Repository(
    val fullName: String,
    val description: String,
    val stars: Int
) {
    fun isHot(): Boolean = stars >= 50
}