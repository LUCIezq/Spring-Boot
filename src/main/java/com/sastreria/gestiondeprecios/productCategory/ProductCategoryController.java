package com.sastreria.gestiondeprecios.productCategory;

import com.sastreria.gestiondeprecios.productCategory.dto.ProductCategoryRequest;
import com.sastreria.gestiondeprecios.productCategory.dto.ProductCategoryResponse;
import com.sastreria.gestiondeprecios.productCategory.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/product-categories")
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryController {
    private final ProductCategoryService service;
    private final ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductCategoryResponse> create(@Valid @RequestBody ProductCategoryRequest request) {
        log.info("Request para crear producto tipo({})", request.name());

        ProductCategory product = service.save(mapper.toEntity(request));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(
                location
        ).body(mapper.toDto(product));
    }
}
