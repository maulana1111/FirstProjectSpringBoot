package com.second_project.second_project.controller;

import com.second_project.second_project.model.Course;
import com.second_project.second_project.repository.CourseRepository;
import com.second_project.second_project.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course")
    public ResponseEntity<Object> findAll(@RequestParam(name = "", defaultValue = "") String name)
    {
        try{
            List<Course> courses;
            if(StringUtils.hasText(name))
            {
                courses = courseRepository.findCourseByTitle(name);
            }else{
                courses = courseRepository.findAll();
            }
            if(courses.isEmpty())
            {
                return ResponseHandler.generateResponse("Data Course Empty", HttpStatus.MULTI_STATUS, null);
            }
            return ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, courses);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Object> courseByid(@PathVariable("id") Long id)
    {
        Course course = courseRepository.findCourseById(id);
        try{
            if(ObjectUtils.isEmpty(course))
            {
                return ResponseHandler.generateResponse("Data Not Found", HttpStatus.MULTI_STATUS, null);
            }
            return ResponseHandler.generateResponse("Success Retrived Data", HttpStatus.OK, course);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(path = "/course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@RequestBody Course course)
    {
        try{
            return ResponseHandler.generateResponse("Success Created Course", HttpStatus.CREATED, courseRepository.save(course));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(path = "/course/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateCourse(@PathVariable("id") Long id, @RequestBody Course course)
    {
        Course courseData = courseRepository.findCourseById(id);
        try{
            if(!ObjectUtils.isEmpty(courseData))
            {
                return ResponseHandler.generateResponse("Course Updated", HttpStatus.OK, courseRepository.save(course));
            }else{
                return ResponseHandler.generateResponse("Course Not Found", HttpStatus.MULTI_STATUS, null);
            }
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
