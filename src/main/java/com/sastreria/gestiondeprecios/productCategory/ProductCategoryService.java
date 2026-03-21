package com.sastreria.gestiondeprecios.productCategory;

public interface ProductCategoryService {
    ProductCategory save(ProductCategory productCategory);

    ProductCategory findByName(String name);

    boolean existsByName(String name);
}
