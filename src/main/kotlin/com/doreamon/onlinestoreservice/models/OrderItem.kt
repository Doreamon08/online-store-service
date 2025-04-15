package com.doreamon.onlinestoreservice.models

import jakarta.persistence.*

@Entity
@Table(name = "order_items")
data class OrderItem (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: Order? = null,

    val productId: Long = 0,
    val quantity: Int = 0,
    val pricePerUnit: Double = 0.0
)
