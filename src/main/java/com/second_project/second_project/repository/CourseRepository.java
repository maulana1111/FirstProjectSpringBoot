package com.second_project.second_project.repository;

import com.second_project.second_project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    public List<Course> findCourseByTitle(String name);

    public Course findCourseById(Long id);
}
