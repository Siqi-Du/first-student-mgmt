package com.spring.first_student_mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.first_student_mgmt.entity.Student;

//CRUD operations
// StudentRepository 继承 JpaRepository 的方法约定，并指定管理 Student，主键类型为 Long
// interface extends继承 interface
// class implements实现 interface 

//JPA ：java语言实现数据库操作的一套规范，Hibernate是JPA规范的实现 执行java对象和DB之间的读写，
//sping Data Jpa: 
// JpaRepository是interface, 
//      SimpleJpaRepository: 库里默认实现类（在dependency里面spring-boot-starter-data-jpa），有@Repository和@Transactional[all public methods already Transactional]
public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> findByLastNameIgnoreCase(String lastName);
}
