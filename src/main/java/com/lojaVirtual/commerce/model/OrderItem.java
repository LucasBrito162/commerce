package com.lojaVirtual.commerce.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referência para o pedido (Order)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Produto comprado
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

    // Quantidade comprada
    private int quantity;

    // Preço unitário no momento da compra
    private BigDecimal precoUnitario;
}
