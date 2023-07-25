package com.example.mutation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

import graphql.kickstart.tools.GraphQLMutationResolver;


@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	StudentService studentService;

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public StudentResponse createStudent (CreateStudentRequest createStudentRequest) {
		LOG.info("Create a Student...");
		return new StudentResponse(studentService.createStudent(createStudentRequest));
	}

	public StudentResponse updateStudent (CreateStudentRequest createStudentRequest, Long id) {
		LOG.info("Update Student..."+id);
		return new StudentResponse(studentService.updateStudent(createStudentRequest, id));
	}

	public Boolean  deleteStudent (Long id) {
		LOG.info("Delete Student "+id);
		studentService.deleteStudent(id);
		return true;
	}
}
