package com.lojaVirtual.commerce.repository;

import org.springframework.stereotype.Repository;

import com.lojaVirtual.commerce.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}