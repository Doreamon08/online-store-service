package com.doreamon.onlinestoreservice.controllers

import com.doreamon.onlinestoreservice.mappers.OrderMapper
import com.doreamon.onlinestoreservice.services.OrderService
import com.example.model.OrderDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val service: OrderService,
    private val mapper: OrderMapper
) {
    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@RequestBody order: OrderDTO) = service.create(mapper.toEntity(order))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updated: OrderDTO) = service.update(id, mapper.toEntity(updated))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}