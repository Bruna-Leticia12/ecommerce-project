package com.ecommerce.project.ecommerce.dto;

import com.ecommerce.project.ecommerce.enums.UserRole;

public record RegisterRequestDTO (String name, String login, String phone, String password,  UserRole role) {
}
