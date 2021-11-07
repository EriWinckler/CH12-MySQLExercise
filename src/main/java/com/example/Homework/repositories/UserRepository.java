package com.example.Homework.repositories;

import com.example.Homework.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLanguage(List language, Sort sort);
}
