package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;

@Service
public class StudentService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StudentRepository studentRepository;
	private Long id;

	public Student getStudentById (long id) {
		return studentRepository.findById(id).get();
	}

	public List<Student> getStudents () {
		return studentRepository.findAll();
	}
	
	public Student createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		student.setAddress(address);
		student = studentRepository.save(student);
		
		List<Subject> subjectsList = new ArrayList<Subject>();
		
		if(createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest : 
					createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);
				
				subjectsList.add(subject);
			}
			
			subjectRepository.saveAll(subjectsList);
			
		}
		
		student.setLearningSubjects(subjectsList);
		
		return student;
	}

	public Boolean deleteStudent (Long id) {
		this.id = id;
		Student student = studentRepository.getById(id);
		Address address = addressRepository.getById(student.getAddress().getId());
		List<Subject> subjs = student.getLearningSubjects();
		for(Subject sub:subjs){
			subjectRepository.deleteById(sub.getId());
		}
		studentRepository.deleteById(id);
		addressRepository.deleteById(address.getId());
		return true;
	}

	public Student updateStudent (CreateStudentRequest createStudentRequest, Long id) {
		Student student = studentRepository.getById(id);
		Address address = addressRepository.getById(student.getAddress().getId());

		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		address = addressRepository.save(address);
		student.setAddress(address);
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student = studentRepository.save(student);
		return student;
	}
}
