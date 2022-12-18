package com.second_project.second_project.repository;

import com.second_project.second_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    public List<User> findUserByName(String name);

    public User findUserById(Long id);

}
