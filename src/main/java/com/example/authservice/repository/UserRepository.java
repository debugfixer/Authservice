package com.example.authservice.repository;

import com.example.authservice.model.Authorities;

import java.util.List;

public interface UserRepository {
    List<Authorities> getUserAuthorities(String user, String password);
}