package com.collegefest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.collegefest.entity.Student;

@Service
public interface StudentService {
	public void createStudentDetails (Student studentDetail);
	public List<Student> getStudentDetail();
	public void deleteStudentDetail (int studentId);
	public Student getStudentDetail (int studentId);
}

