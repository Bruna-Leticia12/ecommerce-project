package com.ecommerce.project.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserEntity {

    @JsonIgnore
    private int id;
    private String name;

}
