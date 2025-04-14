package com.doreamon.onlinestoreservice.controllers

import com.doreamon.onlinestoreservice.mappers.ProductMapper
import com.doreamon.onlinestoreservice.services.ProductService
import com.example.model.ProductDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val service: ProductService,
    private val mapper: ProductMapper
) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = service.getById(id)

    @PostMapping
    fun create(@RequestBody product: ProductDTO) = service.create(mapper.toEntity(product))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updated: ProductDTO) = service.update(id, mapper.toEntity(updated))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}
