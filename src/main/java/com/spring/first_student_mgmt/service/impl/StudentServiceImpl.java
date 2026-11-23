package com.spring.first_student_mgmt.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.first_student_mgmt.dto.StudentDto;
import com.spring.first_student_mgmt.entity.Student;
import com.spring.first_student_mgmt.exception.ResourceNotFoundException;
import com.spring.first_student_mgmt.mapper.StudentMapper;
import com.spring.first_student_mgmt.repository.StudentRepository;
import com.spring.first_student_mgmt.service.StudentService;

import lombok.AllArgsConstructor;


@Service // @Service tell Spring to create Bean for StudentServiceImpl class
@AllArgsConstructor 
// 查询默认使用只读事务：readOnly = true 是只读提示，方便数据库/JPA优化，不是通用的禁止写入开关。
// isolation = READ_COMMITTED：不读取其他事务尚未提交的数据；同一事务内两次查询的结果仍可能不同。
// propagation = REQUIRED：已有事务就加入，没有事务就创建；加入已有事务时通常沿用外层的隔离级别。
// 这些事务注解在通过 Spring Bean 代理调用时生效；同一个类中用 this 调用方法不会开启新的代理事务。
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Override
    // 方法上的配置覆盖类上的配置；省略 readOnly 时默认 false，允许写入。运行时异常默认触发回滚。
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
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
    // 从查询到更新都在同一读写事务中：正常结束提交，运行时异常导致回滚。
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
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
    // 从查询到删除都在同一读写事务中；isolation、propagation 含义见类上的注释。
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student not exist with given id" + studentId));
        studentRepository.deleteById(studentId);
    }
}
