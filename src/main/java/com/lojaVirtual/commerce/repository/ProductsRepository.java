package com.lojaVirtual.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaVirtual.commerce.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
