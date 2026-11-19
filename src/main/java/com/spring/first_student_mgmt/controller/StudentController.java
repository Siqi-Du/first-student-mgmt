package com.spring.first_student_mgmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.first_student_mgmt.dto.StudentDto;
import com.spring.first_student_mgmt.service.StudentService;

import lombok.AllArgsConstructor;

@RestController  //handle http return json
@RequestMapping("/api/students") //define base url for this controller
@AllArgsConstructor 
public class StudentController {
    private StudentService studentService;

    //build Add Student REST API
    // ResponseEntity: generic class(need to pass a type)
    @PostMapping //@PostMapping: map HTTP POST to this method; @RequestBody extract request body and map to the dto parameter obj
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED); //201: created
    }
}
