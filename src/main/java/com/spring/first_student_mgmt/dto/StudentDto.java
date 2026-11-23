package com.spring.first_student_mgmt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
}
