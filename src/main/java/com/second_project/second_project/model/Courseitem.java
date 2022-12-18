package com.second_project.second_project.model;

import com.second_project.second_project.model.data_type_service.StatusActive;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "course_item")
public class Courseitem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Integer id_course;

    private String title_item, file_name, status_item;

    @Enumerated(EnumType.STRING)
    private StatusActive statusActive;
}
