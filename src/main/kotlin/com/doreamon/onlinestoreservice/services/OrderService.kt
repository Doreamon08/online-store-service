package com.doreamon.onlinestoreservice.services

import com.doreamon.onlinestoreservice.models.Order
import com.doreamon.onlinestoreservice.repositories.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val repository: OrderRepository) {
    fun getAll(): List<Order> = repository.findAll()

    fun getById(id: Long): Order = repository.findById(id).orElseThrow { Exception("Order not found") }

    fun create(order: Order): Order = repository.save(order)

    fun update(id: Long, updated: Order): Order {
        val existing = getById(id)
        return repository.save(existing.copy(
            totalPrice = updated.totalPrice,
            status = updated.status)
        )
    }

    fun delete(id: Long) = repository.deleteById(id)
}