package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.category.CategoryDto;
import com.example.fashion_shop_management.entity.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {
}
