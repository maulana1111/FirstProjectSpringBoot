package com.second_project.second_project.repository;

import com.second_project.second_project.model.Courseitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseitemRepository extends JpaRepository<Courseitem, String> {
}
