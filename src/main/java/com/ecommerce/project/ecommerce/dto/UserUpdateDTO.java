package com.ecommerce.project.ecommerce.dto;

public class UserUpdateDTO {
    String name;
    String email;
    String phone;

    public UserUpdateDTO(String nome, String email, String phone) {
        this.name = nome;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserUpdateDTO() {

    }
}


