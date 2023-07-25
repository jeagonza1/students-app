package com.example.query;

import com.example.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.response.StudentResponse;
import com.example.service.StudentService;

import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.ArrayList;
import java.util.List;


@Component
public class Query implements GraphQLQueryResolver {
	
	@Autowired
	StudentService studentService;

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	public StudentResponse getStudent (long id) {
		LOG.info("Search Student By id");
		return new StudentResponse(studentService.getStudentById(id));
	}

	public List<StudentResponse> getStudents () {
		LOG.info("List all of Stdudents...");
		List<Student> students = studentService.getStudents();
		List<StudentResponse> respon = new ArrayList<>() ;

		for(Student student:students){
			StudentResponse studentResponse = new StudentResponse(student);
			respon.add(studentResponse);
		}
		return respon;
	}
}
