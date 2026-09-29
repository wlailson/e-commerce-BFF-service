package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.service.CatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/catalog")
@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService service;

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> findById(@PathVariable Long productId) {
        return service.findById(productId);
    }

    @GetMapping
    ResponseEntity<Page<ProductMinDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "")
            String name,
            Pageable pageable) {
        return service.findAll(name, pageable);
    }

    @PostMapping
    ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO request) {
        return service.insert(request);
    }

    @PutMapping("/{productId}")
    ResponseEntity<ProductDTO> update(
            @PathVariable
            Long productId,
            @RequestBody
            @Valid ProductDTO request) {
        return service.update(productId, request);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteById(@PathVariable Long productId) {
        return service.deleteById(productId);
    }
}
