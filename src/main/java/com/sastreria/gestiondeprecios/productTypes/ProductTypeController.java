package com.sastreria.gestiondeprecios.productTypes;

import com.sastreria.gestiondeprecios.productTypes.dto.ProductTypeRequest;
import com.sastreria.gestiondeprecios.productTypes.dto.ProductTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product-type")
@RequiredArgsConstructor
@Slf4j
public class ProductTypeController {
    private final ProductTypeService service;

    @PostMapping
    public ResponseEntity<ProductTypeResponse> create(@Valid @RequestBody ProductTypeRequest request) {
        return ResponseEntity.ok().body(null);
    }
}
