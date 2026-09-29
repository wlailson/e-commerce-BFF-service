package io.wlailson.github.e_commerce_BFF_service.api.catalog;

public record ProductMinDTO(
        Long id,
        String name,
        Double price,
        String imgUrl) {
}
