package com.lojaVirtual.commerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao; // Quando o pagamento foi criado

    private BigDecimal valor; // Valor total do pagamento

    private String codigoTransacao; // ID da transação no Mercado Pago

    private LocalDateTime dataAtualizacao; // Última atualização do status

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod metodoPagamento;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public enum PaymentStatus {
        PENDING,
        APPROVED,
        CANCELLED
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        PIX,
        BOLETO
    }

}
