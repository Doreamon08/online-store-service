package com.doreamon.onlinestoreservice.mappers

import com.doreamon.onlinestoreservice.models.Product
import com.example.model.ProductDTO
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {

    fun toEntity(dto: ProductDTO): Product

    fun toDto(entity: Product): ProductDTO

    fun toEntityList(dtoList: List<ProductDTO>): List<Product>

    fun toDtoList(entityList: List<Product>): List<ProductDTO>
}
