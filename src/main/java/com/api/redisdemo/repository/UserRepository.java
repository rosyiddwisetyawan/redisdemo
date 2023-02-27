package com.api.redisdemo.repository;

import com.api.redisdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    User getById(Integer id);
}