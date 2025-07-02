package com.wefit.crud.user.userRegisterForWeFit.enums;

public enum Role {

    USER("User"),
    ADMIN("Admin");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}