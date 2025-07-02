package com.wefit.crud.user.userRegisterForWeFit.dto;

import com.wefit.crud.user.userRegisterForWeFit.enums.Role;

public record RegisterDTO(String login, String password, Role role) {
}
