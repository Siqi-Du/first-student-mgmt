package com.spring.first_student_mgmt.mapper;

import com.spring.first_student_mgmt.dto.StudentDto;
import com.spring.first_student_mgmt.entity.Student;

// mapper: map Entity <-> Dto
public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail()
        );
    }

    public static Student mapToStudent(StudentDto studentDto){
        return new Student(
            studentDto.getId(),
            studentDto.getFirstName(),
            studentDto.getLastName(),
            studentDto.getEmail()
        );
    }
}
