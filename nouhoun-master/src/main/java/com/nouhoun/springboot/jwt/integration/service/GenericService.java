package com.nouhoun.springboot.jwt.integration.service;

import java.util.List;

import com.nouhoun.springboot.jwt.integration.domain.User;

public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

}
