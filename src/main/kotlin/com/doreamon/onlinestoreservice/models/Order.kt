package com.doreamon.onlinestoreservice.models

import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val items: List<OrderItem> = listOf(),
    var totalPrice: Double = 0.0,
    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.NULL,
)
enum class OrderStatus {
    CREATED,
    PAID,
    CANCELLED,
    NULL
}