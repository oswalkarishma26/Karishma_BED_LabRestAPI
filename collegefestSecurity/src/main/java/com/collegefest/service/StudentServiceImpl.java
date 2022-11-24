package com.collegefest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collegefest.entity.Student;
import com.collegefest.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository rep;

	@Override
	public void createStudentDetails(Student studentDetail) {
		this.rep.save(studentDetail);
	}

	@Override
	public List<Student> getStudentDetail() {
		// TODO Auto-generated method stub
		return this.rep.findAll();
	}

	@Override
	public void deleteStudentDetail(int studentId) {
		this.rep.deleteById(studentId);
		;
	}

	@Override
	public Student getStudentDetail(int studentId) {
		return this.rep.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Invalid Id Passed"));
	}

}