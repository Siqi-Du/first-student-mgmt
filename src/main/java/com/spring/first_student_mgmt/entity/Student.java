package com.spring.first_student_mgmt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity  // specify the class as a jpa entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment the PK
    private long id;

    @Column (name = "first_name") // if not add this: default column name is field name:firstName
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email_id", nullable = false, unique = true)
    private String email;
}
