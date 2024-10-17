package com.haders.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "staff")
@Data
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

}
