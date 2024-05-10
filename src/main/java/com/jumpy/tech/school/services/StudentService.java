package com.jumpy.tech.school.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;

import com.jumpy.tech.school.entites.Student;
import com.jumpy.tech.school.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import javax.sql.rowset.serial.SerialBlob;

@Service
public class StudentService {
	private StudentRepository studentRepository;
	
	public StudentService(StudentRepository str) {
		this.studentRepository=str;
	}
	
	@PostMapping(path="/students/add")
	public Student saveStudent(String lastName,String firstName,String code,String programId, MultipartFile photo ) throws IOException, SQLException {
		Student stu=new Student();
		if(!photo.isEmpty()) {
			byte[] photoByte= photo.getBytes();
			Blob photoBlob=new SerialBlob(photoByte);
			stu.setPhoto(photoBlob);
		}
	
		return studentRepository.save(stu);
	 
	}
	@GetMapping(path="/student/{code}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] getStudentPhotoFile( String code) throws IOException {
		
		Student student=studentRepository.findByCode(code);
		
		//return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
		return null;
		
	}

}
