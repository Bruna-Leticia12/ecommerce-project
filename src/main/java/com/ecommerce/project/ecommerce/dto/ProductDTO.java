package com.ecommerce.project.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank
        String name,

        @NotNull
        Integer price) {}
