package com.lojaVirtual.commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojaVirtual.commerce.model.User;
import com.lojaVirtual.commerce.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get
    @GetMapping
    public ResponseEntity<List<User>> buscarUsuario() {
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // Get ID
    @GetMapping("/{id}")
    public ResponseEntity<User> buscarId(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Put
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarDados(@PathVariable Long id, @RequestBody User atualizarUser) {
        return userRepository.findById(id)
                .map(existeUser -> {
                    existeUser.setNome(atualizarUser.getNome());
                    existeUser.setCpf(atualizarUser.getCpf());
                    existeUser.setEmail(atualizarUser.getEmail());
                    existeUser.setTelefone(atualizarUser.getTelefone());

                    User saved = userRepository.save(existeUser);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
