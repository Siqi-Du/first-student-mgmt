package com.spring.first_student_mgmt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.first_student_mgmt.service.StudentService;

import lombok.AllArgsConstructor;

@RestController  //handle http return json
@RequestMapping("/api/students") //define base url for this controller
@AllArgsConstructor 
public class StudentController {
    private StudentService studentService;

    //build Add Student REST API
    
}
