package com.jacaranda.studentDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.studentDB.model.Student;
import com.jacaranda.studentDB.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public Student getStudent(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Student> getStudents(){
		return repository.findAll();
	}
	
	public Student add(Student s) {
		return repository.save(s);
	}
	
	public void delete(Student s) {
		repository.delete(s);
	}
	
	public Student update(Student s) {
		if (getStudent(s.getId())!= null) {
			return repository.save(s);
		}
		else {
			return null;
		}
	}
}
