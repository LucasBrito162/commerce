package com.lojaVirtual.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaVirtual.commerce.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
