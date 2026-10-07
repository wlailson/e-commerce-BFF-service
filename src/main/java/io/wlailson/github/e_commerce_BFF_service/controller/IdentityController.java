package io.wlailson.github.e_commerce_BFF_service.controller;

import io.wlailson.github.e_commerce_BFF_service.api.identity.LoginRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserRequestDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseDTO;
import io.wlailson.github.e_commerce_BFF_service.api.identity.UserResponseMinDTO;
import io.wlailson.github.e_commerce_BFF_service.service.IdentityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Cadastro, consulta e autenticação de usuários.")
public class IdentityController {

    private final IdentityService service;

    @GetMapping("/{userId}")
    @Operation(summary = "Consultar usuário", description = "Busca os dados completos de um usuário pelo identificador.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado.")
    public ResponseEntity<UserResponseDTO> getUserById(
            @Parameter(description = "Identificador do usuário.", example = "42")
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna uma página com os dados resumidos dos usuários.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Página de usuários.")
    public ResponseEntity<Page<UserResponseMinDTO>> getAllUsers(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.getAllUsers(pageable));
    }

    @GetMapping("/me")
    @Operation(summary = "Consultar usuário autenticado", description = "Retorna os dados do usuário associado ao token atual.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Usuário autenticado.")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {
        return ResponseEntity.ok(service.getCurrentUser());
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria uma conta com os dados informados.")
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado.")
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody @Valid UserRequestDTO request) {
        return ResponseEntity.ok(service.postUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Valida as credenciais e retorna o token de autenticação.")
    @ApiResponse(responseCode = "200", description = "Autenticação realizada.")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO request) {

        return ResponseEntity.ok(service.login(request));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados do usuário identificado.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado.")
    public ResponseEntity<UserResponseDTO> putUser(
            @Parameter(description = "Identificador do usuário.", example = "42")
            @PathVariable Long userId,
            @RequestBody @Valid UserRequestDTO request) {
        return ResponseEntity.ok(service.putUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Excluir usuário", description = "Remove o usuário identificado.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "204", description = "Usuário excluído.")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "Identificador do usuário.", example = "42")
            @PathVariable Long userId) {
        service.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
