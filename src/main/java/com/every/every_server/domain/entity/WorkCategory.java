package com.every.every_server.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "work_category")
@Getter
@Setter
public class WorkCategory {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "name")
    private String name;
}
