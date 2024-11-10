package com.example.fashion_shop_management.mapper;

import com.example.fashion_shop_management.dto.request.PromotionDto;
import com.example.fashion_shop_management.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface PromotionMapper extends EntityMapper<PromotionDto, Promotion> {
}
