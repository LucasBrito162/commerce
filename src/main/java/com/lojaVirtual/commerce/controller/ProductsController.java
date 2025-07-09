package com.lojaVirtual.commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojaVirtual.commerce.model.Products;
import com.lojaVirtual.commerce.repository.ProductsRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // GET
    @GetMapping
    public List<Products> buscarProduto() {
        return productsRepository.findAll();
    }

    // GETID
    @GetMapping("/{id}")
    public ResponseEntity<Products> buscarProdutosId(@PathVariable Long id) {
        return productsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Products> create(@RequestBody Products products) {
        Products saved = productsRepository.save(products);
        return ResponseEntity.ok(saved);
    }

    // PUT
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Products> atualizarProduto(@PathVariable Long id, @RequestBody Products atualizarProducts) {
        return productsRepository.findById(id)
                .map(existeProducts -> {
                    existeProducts.setNome(atualizarProducts.getNome());
                    existeProducts.setDescricao(atualizarProducts.getDescricao());
                    existeProducts.setPreco(atualizarProducts.getPreco());
                    existeProducts.setQuantidade(atualizarProducts.getQuantidade());

                    Products saved = productsRepository.save(existeProducts);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());

    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        if (!productsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();

        }
        productsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
