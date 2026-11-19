package com.spring.first_student_mgmt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO: transfer data between client and server
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class StudentDto { // post request body need to match the field names!!!!!
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
