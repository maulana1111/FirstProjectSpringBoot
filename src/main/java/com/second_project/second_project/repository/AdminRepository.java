package com.second_project.second_project.repository;

import com.second_project.second_project.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admins, String> {
    public List<Admins> findAdminByName(String name);

    public Admins findAdminById(Long id);
}
