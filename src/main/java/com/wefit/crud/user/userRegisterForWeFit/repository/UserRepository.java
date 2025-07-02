package com.wefit.crud.user.userRegisterForWeFit.repository;

import com.wefit.crud.user.userRegisterForWeFit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLogin(String login);
}