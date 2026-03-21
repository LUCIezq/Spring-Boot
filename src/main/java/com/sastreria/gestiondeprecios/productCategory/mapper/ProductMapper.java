package com.sastreria.gestiondeprecios.productCategory.mapper;

import com.sastreria.gestiondeprecios.productCategory.ProductCategory;
import com.sastreria.gestiondeprecios.productCategory.dto.ProductCategoryRequest;
import com.sastreria.gestiondeprecios.productCategory.dto.ProductCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductCategory toEntity(ProductCategoryRequest request) {
        return ProductCategory.builder()
                .name(request.name())
                .description(request.description())
                .active(true)
                .build();
    }

    public ProductCategoryResponse toDto(ProductCategory productCategory) {
        return ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .createdAt(productCategory.getCreatedAt())
                .build();
    }
}
