package com.sastreria.gestiondeprecios.productCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean findByName(String name);

    boolean existsByName(String name);
}
