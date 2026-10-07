package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductDTO;
import io.wlailson.github.e_commerce_BFF_service.api.catalog.ProductMinDTO;
import io.wlailson.github.e_commerce_BFF_service.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/catalog")
@RestController
@RequiredArgsConstructor
@Tag(name = "Catálogo", description = "Consulta e manutenção do catálogo de produtos.")
public class CatalogController {

    private final CatalogService service;

    @GetMapping("/{productId}")
    @Operation(summary = "Consultar produto", description = "Busca um produto completo pelo identificador.")
    @ApiResponse(responseCode = "200", description = "Produto encontrado.")
    public ResponseEntity<ProductDTO> findById(
            @Parameter(description = "Identificador do produto.", example = "25")
            @PathVariable Long productId) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    @Operation(summary = "Listar produtos", description = "Busca produtos por nome e retorna uma página de resultados.")
    @ApiResponse(responseCode = "200", description = "Página de produtos.")
    public ResponseEntity<Page<ProductMinDTO>> findAll(
            @Parameter(description = "Filtro opcional pelo nome do produto.", example = "cafeteira")
            @RequestParam(name = "name", defaultValue = "")
            String name,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(name, pageable));
    }

    @GetMapping("/batch")
    @Operation(summary = "Consultar produtos por identificadores", description = "Retorna os produtos correspondentes aos identificadores informados.")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados.")
    public ResponseEntity<List<ProductDTO>> findAllByIds(
            @Parameter(description = "Lista de identificadores de produtos.", example = "25,26")
            @RequestParam List<Long> productIds) {
        return ResponseEntity.ok(service.findAllByIds(productIds));
    }

    @PostMapping
    @Operation(summary = "Cadastrar produto", description = "Cria um produto no catálogo.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Produto cadastrado.")
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO request) {
        return ResponseEntity.ok(service.insert(request));
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Produto atualizado.")
    public ResponseEntity<ProductDTO> update(
            @Parameter(description = "Identificador do produto.", example = "25")
            @PathVariable
            Long productId,
            @RequestBody
            @Valid ProductDTO request) {
        return ResponseEntity.ok(service.update(productId, request));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Excluir produto", description = "Remove um produto do catálogo.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "204", description = "Produto excluído.")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Identificador do produto.", example = "25")
            @PathVariable Long productId) {
        service.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}
