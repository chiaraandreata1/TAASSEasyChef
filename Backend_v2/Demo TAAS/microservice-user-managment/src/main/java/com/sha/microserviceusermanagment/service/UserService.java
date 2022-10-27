package com.sha.microserviceusermanagment.service;

import com.sha.microserviceusermanagment.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);
}