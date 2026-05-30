package vallab.practice.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUrl: String
)

val dummyProducts: List<Product> = listOf(
    Product(
        1,
        "상품 1",
        10000,
        "https://images.unsplash.com/photo-1752027992576-703170155533?w=156&h=158&fit=crop"
    ),
    Product(
        2,
        "상품 2",
        12000,
        "https://images.unsplash.com/photo-1618401375129-8c6a8ef678b9?w=156&h=158&fit=crop"
    ),
    Product(
        3,
        "상품 3",
        10000,
        "https://images.unsplash.com/photo-1771580824691-f5e420b9400e?w=156&h=158&fit=crop"
    ),
    Product(
        4,
        "상품 4",
        12000,
        "https://images.unsplash.com/photo-1628793528235-04a56ca7785f?w=156&h=158&fit=crop"
    ),
    Product(
        5,
        "상품 5",
        10000,
        "https://images.unsplash.com/photo-1752027992312-16d5ce76b911?w=156&h=158&fit=crop"
    ),
    Product(
        6,
        "상품 6",
        12000,
        "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?w=156&h=158&fit=crop"
    ),
)