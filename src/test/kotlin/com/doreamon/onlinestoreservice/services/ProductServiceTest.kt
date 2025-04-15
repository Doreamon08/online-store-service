package com.doreamon.onlinestoreservice.services

import com.doreamon.onlinestoreservice.models.Product
import com.doreamon.onlinestoreservice.repositories.ProductRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional
import org.junit.jupiter.api.BeforeEach

@ExtendWith(MockitoExtension::class)
class ProductServiceTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService(productRepository)
    }

    @Test
    fun `should return all products`() {
        // given
        val products = listOf(
            Product(id = 1, name = "Product 1", price = 100.0),
            Product(id = 2, name = "Product 2", price = 200.0)
        )
        `when`(productRepository.findAll()).thenReturn(products)

        // when
        val result = productService.getAll()

        // then
        assertEquals(products, result)
        verify(productRepository, times(1)).findAll()
    }

    @Test
    fun `should return product by id when product exists`() {
        // given
        val id = 1L
        val product = Product(id = id, name = "Product 1", price = 100.0)
        `when`(productRepository.findById(id)).thenReturn(Optional.of(product))

        // when
        val result = productService.getById(id)

        // then
        assertEquals(product, result)
        verify(productRepository, times(1)).findById(id)
    }

    @Test
    fun `should throw exception when product not found`() {
        // given
        val id = 1L
        `when`(productRepository.findById(id)).thenReturn(Optional.empty())

        // when & then
        val exception = assertThrows(Exception::class.java) {
            productService.getById(id)
        }
        assertEquals("Product not found", exception.message)
        verify(productRepository, times(1)).findById(id)
    }

    @Test
    fun `should create product`() {
        // given
        val product = Product(name = "New Product", price = 150.0)
        val savedProduct = Product(id = 1, name = "New Product", price = 150.0)
        `when`(productRepository.save(product)).thenReturn(savedProduct)

        // when
        val result = productService.create(product)

        // then
        assertEquals(savedProduct, result)
        verify(productRepository, times(1)).save(product)
    }

    @Test
    fun `should update product when product exists`() {
        // given
        val id = 1L
        val existingProduct = Product(id = id, name = "Product 1", price = 100.0)
        val updatedData = Product(name = "Updated Product", price = 200.0)
        val expectedProduct = Product(id = id, name = "Updated Product", price = 200.0)

        `when`(productRepository.findById(id)).thenReturn(Optional.of(existingProduct))
        `when`(productRepository.save(expectedProduct)).thenReturn(expectedProduct)

        // when
        val result = productService.update(id, updatedData)

        // then
        assertEquals(expectedProduct, result)
        verify(productRepository, times(1)).findById(id)
        verify(productRepository, times(1)).save(expectedProduct)
    }

    @Test
    fun `should delete product`() {
        // given
        val id = 1L
        doNothing().`when`(productRepository).deleteById(id)

        // when
        productService.delete(id)

        // then
        verify(productRepository, times(1)).deleteById(id)
    }
}