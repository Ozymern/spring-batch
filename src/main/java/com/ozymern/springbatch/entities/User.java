package com.ozymern.springbatch.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class User {


    @Id
    @GeneratedValue
    private Integer id;

    private int age;

    private String email;

    @Column(name = "last_name")
    private String lastName;

    private  String name;

    private String sex;


}
