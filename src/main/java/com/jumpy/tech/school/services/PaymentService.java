package com.jumpy.tech.school.services;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jumpy.tech.school.dtos.PaymentDTO;
import com.jumpy.tech.school.entites.Payment;
import com.jumpy.tech.school.entites.PaymentStatus;
import com.jumpy.tech.school.entites.PaymentType;
import com.jumpy.tech.school.entites.Student;
import com.jumpy.tech.school.repository.PaymentRepository;
import com.jumpy.tech.school.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentService {
	
	private StudentRepository studentRepository;
	private PaymentRepository paymentRepository;
	
	public PaymentService(StudentRepository studentRepository,PaymentRepository paymentRepository) {
		this.paymentRepository=paymentRepository;
		this.studentRepository=studentRepository;
	}
	
	@PostMapping(path="/payments", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public Payment savePayment(
			MultipartFile file, PaymentDTO paymentDto
		) throws IOException {
		Path folderPath=Paths.get(System.getProperty("user.home"),"SchoolName","Payments");
		if(!Files.exists(folderPath)) {
			Files.createDirectories(folderPath);
			
		}
		String fileName=UUID.randomUUID().toString();
		Path filePath=Paths.get(System.getProperty("user.home"),"SchoolName","Payments",fileName+".pdf");
		
		Files.copy(file.getInputStream(), filePath);
		Student student=studentRepository.findByCode(paymentDto.getStudentCode());
		Payment pay=Payment.builder()
				.date(paymentDto.getDate())
				.type(paymentDto.getType())
				.student(student)
				.amount(paymentDto.getAmount())
				.status(PaymentStatus.CREATED)
				.file(filePath.toUri().toString())
				.build();
		
		return paymentRepository.save(pay);
		
	}
	@PutMapping("/payments/{id}")
	public Payment updatePaymentStatus(PaymentStatus status,  Long id) {
		Payment pay=paymentRepository.findById(id).get();
		pay.setStatus(status);
		return paymentRepository.save(pay);
	}
	
	@GetMapping(path="/payments/{paymentId}",produces=MediaType.APPLICATION_PDF_VALUE)
	public byte[] getPaymentFile( Long paymentId) throws IOException {
		
		Payment payment=paymentRepository.findById(paymentId).get();
		
		return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
		
	}
}
