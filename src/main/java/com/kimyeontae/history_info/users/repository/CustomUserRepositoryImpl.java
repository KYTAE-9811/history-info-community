package com.kimyeontae.history_info.users.repository;

import com.kimyeontae.history_info.users.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public class CustomUserRepositoryImpl implements CustomUserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Users> findByUsername(String username) {
        Users user = em.find(Users.class, username);
        Optional<Users> findUser = Optional.ofNullable(user);
        return findUser;
    }
}
