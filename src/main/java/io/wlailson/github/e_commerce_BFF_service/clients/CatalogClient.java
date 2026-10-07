package io.wlailson.github.e_commerce_BFF_service.clients;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.config.FeignConfig;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "catalog-service",
        url = "${services.catalog.url}",
        configuration = FeignConfig.class
)
public interface CatalogClient {

    @GetMapping("/{productId}")
    ProductDTO findById(@PathVariable Long productId);

    @GetMapping
    Page<ProductMinDTO> findAll(
            @RequestParam(name = "name", defaultValue = "")
            String name,
            Pageable pageable);

    @PostMapping
    ProductDTO insert(@Valid @RequestBody ProductDTO request);

    @PutMapping("/{productId}")
    ProductDTO update(
            @PathVariable
            Long productId,
            @RequestBody
            @Valid ProductDTO request);

    @DeleteMapping("/{productId}")
    void deleteById(@PathVariable Long productId);

    @GetMapping("/batch")
    List<ProductDTO> findAllByIds(@RequestParam List<Long> productIds);

}
