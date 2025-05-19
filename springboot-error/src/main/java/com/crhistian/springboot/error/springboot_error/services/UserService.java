package com.crhistian.springboot.error.springboot_error.services;

import java.util.List;
import java.util.Optional;

import com.crhistian.springboot.error.springboot_error.models.domain.User;

public interface UserService {

    List<User> findyAll();
    Optional<User> findById(Long id);

}
