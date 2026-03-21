package com.sastreria.gestiondeprecios.productCategory;

import com.sastreria.gestiondeprecios.exceptions.ProductType.ProductTypeAlreadyExist;
import com.sastreria.gestiondeprecios.util.TextNormalizer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository repository;

    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        String normalizeName = TextNormalizer.normalize(productCategory.getName());
        if (existsByName(normalizeName)) {
            throw new ProductTypeAlreadyExist("Ya se encuentra una categoria con este nombre");
        }
        productCategory.setName(normalizeName);
        try {
            return repository.save(productCategory);
        } catch (DataIntegrityViolationException e) {
            throw new ProductTypeAlreadyExist("Ya se encuentra una categoria con este nombre");
        }
    }

    @Override
    public ProductCategory findByName(String name) {
        return null;
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
