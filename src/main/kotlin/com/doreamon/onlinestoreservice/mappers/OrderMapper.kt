package com.doreamon.onlinestoreservice.mappers

import com.doreamon.onlinestoreservice.models.Order
import com.example.model.OrderDTO
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface OrderMapper {

    fun toEntity(dto: OrderDTO): Order

    fun toDto(entity: Order): OrderDTO

    fun toEntityList(dtoList: List<OrderDTO>): List<Order>

    fun toDtoList(entityList: List<Order>): List<OrderDTO>
}