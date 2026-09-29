package io.wlailson.github.e_commerce_BFF_service.api.catalog;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record ProductDTO(
        Long id,
        @NotBlank(message = "Campo requerido")
        @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
        String name,
        @NotBlank(message = "Campo requerido")
        @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
        String description,
        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "O preço deve ser positivo")
        Double price,
        String imgUrl,
        @NotEmpty(message = "Deve ter pelo menos uma categoria")
        List<@NotNull @Valid CategoryDTO> categories) {


}
