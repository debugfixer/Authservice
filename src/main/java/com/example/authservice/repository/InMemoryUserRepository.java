package com.example.authservice.repository;

import com.example.authservice.model.Authorities;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {
    // Простое хранилище для примера: Имя -> Пароль
    private final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();
    // Хранилище прав: Имя -> Список прав
    private final ConcurrentHashMap<String, List<Authorities>> authorities = new ConcurrentHashMap<>();

    public InMemoryUserRepository() {
        // Добавим тестовых пользователей
        users.put("admin", "adminpass");
        authorities.put("admin", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));

        users.put("user", "userpass");
        authorities.put("user", List.of(Authorities.READ));
    }

    @Override
    public List<Authorities> getUserAuthorities(String user, String password) {
        if (users.containsKey(user) && users.get(user).equals(password)) {
            return authorities.getOrDefault(user, List.of());
        }
        return List.of();
    }
}