package com.spring.first_student_mgmt.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.first_student_mgmt.dto.StudentDto;
import com.spring.first_student_mgmt.entity.Student;
import com.spring.first_student_mgmt.exception.ResourceNotFoundException;
import com.spring.first_student_mgmt.mapper.StudentMapper;
import com.spring.first_student_mgmt.repository.StudentRepository;
import com.spring.first_student_mgmt.service.StudentService;

import lombok.AllArgsConstructor;


@Service // @Service tell Spring to create Bean for StudentServiceImpl class
@AllArgsConstructor 
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        // convert dto to entity and save in repository
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        // return dto
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Student not exist with given id: " + studentId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student)) 
                .collect(Collectors.toList()); //stream API with collectors
    }

    @Override
    public List<StudentDto> getStudentsByLastName(String lastName) {
        return studentRepository.findByLastNameIgnoreCase(lastName).stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto) {
        Student student = studentRepository.findById(studentId).orElseThrow(
            () -> new ResourceNotFoundException("Student not exist with given id" + studentId)
        );
        student.setFirstName(updatedStudentDto.getFirstName());
        student.setLastName(updatedStudentDto.getLastName());
        student.setEmail(updatedStudentDto.getEmail());
        Student updatedStudent = studentRepository.save(student); //perform both save and update operation
        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not exist with given id" + studentId));
        studentRepository.deleteById(studentId);
    }
}
