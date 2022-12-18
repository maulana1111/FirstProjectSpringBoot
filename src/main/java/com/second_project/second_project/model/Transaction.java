package com.second_project.second_project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Integer course_id, user_id;

    @Transient
    private Long nominal;

    @Temporal(TemporalType.DATE)
    private Date date_transaction;

    private String proof_transaction;

}
