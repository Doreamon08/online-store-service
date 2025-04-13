package com.doreamon.onlinestoreservice.controllers

import com.example.model.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController {

    @GetMapping
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Apple", 10.0),
            Product(3, "Orange", 200.0)
        )
    }
}
