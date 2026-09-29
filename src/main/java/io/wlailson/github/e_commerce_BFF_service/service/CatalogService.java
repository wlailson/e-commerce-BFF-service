package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.CatalogClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient client;

    public ResponseEntity<ProductDTO> findById(Long id) {
        return client.findById(id);
    }

    public ResponseEntity<Page<ProductMinDTO>> findAll(String name, Pageable pageable) {
        return client.findAll(name, pageable);
    }

    public ResponseEntity<ProductDTO> insert(ProductDTO request) {
        return client.insert(request);
    }

    public ResponseEntity<ProductDTO> update(Long productId, ProductDTO request) {
        return client.update(productId, request);
    }

    public ResponseEntity<Void> deleteById(Long productId) {
        return client.deleteById(productId);
    }

}
