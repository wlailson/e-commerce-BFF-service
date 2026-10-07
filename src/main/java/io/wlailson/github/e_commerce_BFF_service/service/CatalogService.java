package io.wlailson.github.e_commerce_BFF_service.service;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.clients.CatalogClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient client;

    public ProductDTO findById(Long id) {
        return client.findById(id);
    }

    public List<ProductDTO> findAllByIds(List<Long> productIds) {
        return client.findAllByIds(productIds);
    }

    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        return client.findAll(name, pageable);
    }

    public ProductDTO insert(ProductDTO request) {
        return client.insert(request);
    }

    public ProductDTO update(Long productId, ProductDTO request) {
        return client.update(productId, request);
    }

    public void deleteById(Long productId) {
        client.deleteById(productId);
    }

}
