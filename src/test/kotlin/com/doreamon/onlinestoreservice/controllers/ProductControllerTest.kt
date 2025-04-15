package com.doreamon.onlinestoreservice.controllers

import com.doreamon.onlinestoreservice.mappers.ProductMapper
import com.doreamon.onlinestoreservice.models.Product
import com.doreamon.onlinestoreservice.services.ProductService
import com.example.model.ProductDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

@ExtendWith(MockitoExtension::class)
class ProductControllerTest {

    @Mock
    private lateinit var productService: ProductService

    @Mock
    private lateinit var productMapper: ProductMapper

    private lateinit var productController: ProductController

    private lateinit var mockMvc: MockMvc
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        productController = ProductController(productService, productMapper)
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build()
        objectMapper = ObjectMapper().registerKotlinModule()
    }

    @Test
    fun `should return all products`() {
        // given
        val products = listOf(
            Product(id = 1, name = "Product 1", price = 100.0),
            Product(id = 2, name = "Product 2", price = 200.0)
        )
        `when`(productService.getAll()).thenReturn(products)

        // when & then
        mockMvc.perform(get("/products"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Product 1"))
            .andExpect(jsonPath("$[0].price").value(100.0))
            .andExpect(jsonPath("$[1].id").value(2))
            .andExpect(jsonPath("$[1].name").value("Product 2"))
            .andExpect(jsonPath("$[1].price").value(200.0))

        verify(productService, times(1)).getAll()
    }

    @Test
    fun `should return product by id`() {
        // given
        val product = Product(id = 1, name = "Product 1", price = 100.0)
        `when`(productService.getById(1)).thenReturn(product)

        // when & then
        mockMvc.perform(get("/products/1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Product 1"))
            .andExpect(jsonPath("$.price").value(100.0))

        verify(productService, times(1)).getById(1)
    }

    @Test
    fun `should create product`() {
        // given
        val productDTO = ProductDTO(name = "New Product", price = 150.0)
        val productEntity = Product(name = "New Product", price = 150.0)
        val savedProduct = Product(id = 1, name = "New Product", price = 150.0)

        `when`(productMapper.toEntity(productDTO)).thenReturn(productEntity)
        `when`(productService.create(productEntity)).thenReturn(savedProduct)

        // when & then
        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("New Product"))
            .andExpect(jsonPath("$.price").value(150.0))

        verify(productMapper, times(1)).toEntity(productDTO)
        verify(productService, times(1)).create(productEntity)
    }

    @Test
    fun `should update product`() {
        // given
        val id = 1L
        val productDTO = ProductDTO(name = "Updated Product", price = 175.0)
        val productEntity = Product(name = "Updated Product", price = 175.0)
        val updatedProduct = Product(id = id, name = "Updated Product", price = 175.0)

        `when`(productMapper.toEntity(productDTO)).thenReturn(productEntity)
        `when`(productService.update(id, productEntity)).thenReturn(updatedProduct)

        // when & then
        mockMvc.perform(put("/products/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productDTO)))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Updated Product"))
            .andExpect(jsonPath("$.price").value(175.0))

        verify(productMapper, times(1)).toEntity(productDTO)
        verify(productService, times(1)).update(id, productEntity)
    }

    @Test
    fun `should delete product`() {
        // given
        val id = 1L
        doNothing().`when`(productService).delete(id)

        // when & then
        mockMvc.perform(delete("/products/{id}", id))
            .andExpect(status().isOk)

        verify(productService, times(1)).delete(id)
    }
}
