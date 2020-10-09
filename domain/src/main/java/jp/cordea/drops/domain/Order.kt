package jp.cordea.drops.domain

class Order(
    val id: String,
    val status: Status,
    val items: List<Item>,
    val isCancelable: Boolean
) {
    enum class Status {
        CONFIRMED,
        SHIPPED,
        DELIVERED,
        CANCELED
    }
}
