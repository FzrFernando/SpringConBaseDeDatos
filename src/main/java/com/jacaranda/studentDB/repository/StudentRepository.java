package com.jacaranda.studentDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.studentDB.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
