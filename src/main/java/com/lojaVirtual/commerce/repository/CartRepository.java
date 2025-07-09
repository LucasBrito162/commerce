package com.lojaVirtual.commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaVirtual.commerce.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    // Exemplo: buscar itens do carrinho por usuário
    List<CartItem> findByUserId(Long userId);

}
