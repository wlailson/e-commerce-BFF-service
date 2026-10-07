package io.wlailson.github.e_commerce_BFF_service.api.order.conversions;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados resumidos do produto em um item de pedido.")
public record Product(
        @Schema(description = "Identificador do produto.", example = "25")
        Long id,
        @Schema(description = "Nome do produto.", example = "Cafeteira")
        String name,
        @Schema(description = "URL ou caminho da imagem.", example = "https://example.com/cafeteira.jpg")
        String imgUrl
)  {

    public Product(ProductDTO response) {
        this(
                response.id(),
                response.name(),
                response.imgUrl());
    }
}
