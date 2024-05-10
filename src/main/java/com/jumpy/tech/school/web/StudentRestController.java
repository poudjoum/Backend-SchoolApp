package com.jumpy.tech.school.web;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jumpy.tech.school.entites.Student;
import com.jumpy.tech.school.response.StudentResponse;
import com.jumpy.tech.school.services.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentRestController {
	private StudentService stuService;

	public StudentRestController(StudentService studentService) {
		this.stuService=studentService;
	}
	@PostMapping(path="/add",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<StudentResponse>save(
			@RequestParam("lastName")String lastName,
			@RequestParam("firstName")String firstName,
			@RequestParam("code")String code,
			@RequestParam("ProgramId")String programId,
			@RequestParam("photo")MultipartFile photo
			) throws SQLException, IOException{
		
		Student savedStudent=stuService.saveStudent(lastName,firstName,code, programId,photo);
		StudentResponse response=new StudentResponse(
				savedStudent.getId(),
				savedStudent.getFirstName(),
				savedStudent.getLastName(),
				savedStudent.getCode(),
				savedStudent.getProgramId(),
				savedStudent.getPhoto());
		return ResponseEntity.ok(response);
	}
}
