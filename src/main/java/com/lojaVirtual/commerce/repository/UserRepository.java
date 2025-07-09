package com.lojaVirtual.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojaVirtual.commerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
