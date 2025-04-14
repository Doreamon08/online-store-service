package com.doreamon.onlinestoreservice.repositories

import com.doreamon.onlinestoreservice.models.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
