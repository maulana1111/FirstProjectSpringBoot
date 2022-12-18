package com.second_project.second_project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long price;

    @Transient
    private Integer id_admin_created;

    private String title, thumbnail;

    @Lob
    private String description;

    @Temporal(TemporalType.DATE)
    private Date created_at, updated_at;
}
