package com.spring.first_student_mgmt.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // build Add Student REST API
    // ResponseEntity: generic class(need to pass a type)
    @PostMapping //@PostMapping: map HTTP POST to this method; @RequestBody extract request body and map to the dto parameter obj
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        StudentDto savedStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED); //201: created
    }

    // get student by id REST API
    @GetMapping("{id}") //map incoming HTTP GET requests to this method; @PathVariable bind the path variable id to the parameter studentId
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId){
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDto); // A shortcut for creating a ResponseEntity with the given body and the
                                              // status set to OK.

    }

    //build get all students REST API
    @GetMapping 
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    //build update student API
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId, 
                                                    @RequestBody StudentDto studentDto){ //extract request body json and convert into a dto java object
        StudentDto updatedStudentDto = studentService.updateStudent(studentId, studentDto);
        return ResponseEntity.ok(updatedStudentDto);
    }

    //build delete student API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student deleted successfully.");
    }
}
