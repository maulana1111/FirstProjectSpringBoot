package com.second_project.second_project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "course_user")
public class Courseuser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Integer id_course, id_user;

    @Temporal(TemporalType.DATE)
    private Date buying_at;
}
