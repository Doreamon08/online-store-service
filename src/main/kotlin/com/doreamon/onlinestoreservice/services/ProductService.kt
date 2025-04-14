package com.doreamon.onlinestoreservice.services

import com.doreamon.onlinestoreservice.models.Product
import com.doreamon.onlinestoreservice.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val repository: ProductRepository) {

    fun getAll(): List<Product> = repository.findAll()

    fun getById(id: Long): Product = repository.findById(id).orElseThrow { Exception("Product not found") }

    fun create(product: Product): Product = repository.save(product)

    fun update(id: Long, updated: Product): Product {
        val existing = getById(id)
        return repository.save(existing.copy(name = updated.name, price = updated.price))
    }

    fun delete(id: Long) = repository.deleteById(id)
}
