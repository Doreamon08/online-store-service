package com.doreamon.onlinestoreservice.repositories

import com.doreamon.onlinestoreservice.models.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>