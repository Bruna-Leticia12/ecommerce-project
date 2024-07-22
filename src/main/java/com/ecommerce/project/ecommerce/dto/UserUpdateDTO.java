package com.ecommerce.project.ecommerce.dto;

public class UserUpdateDTO {
    String name;
    String login;
    String phone;

    public UserUpdateDTO(String nome, String login, String phone) {
        this.name = nome;
        this.login = login;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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


