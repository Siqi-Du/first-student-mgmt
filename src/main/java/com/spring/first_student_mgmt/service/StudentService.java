package com.spring.first_student_mgmt.service;

import java.util.List;

import com.spring.first_student_mgmt.dto.StudentDto;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();
}
