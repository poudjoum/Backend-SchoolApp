package com.jumpy.tech.school.web;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jumpy.tech.school.entites.Payment;
import com.jumpy.tech.school.entites.PaymentStatus;
import com.jumpy.tech.school.entites.PaymentType;
import com.jumpy.tech.school.entites.Student;
import com.jumpy.tech.school.repository.PaymentRepository;
import com.jumpy.tech.school.repository.StudentRepository;
import com.jumpy.tech.school.services.PaymentService;

@RestController
@CrossOrigin("*")

public class PaymentRestController {
	
	private StudentRepository studentRepository;
	private PaymentRepository paymentRepository;
	private PaymentService paymentService;

	public PaymentRestController(PaymentService paymentService,StudentRepository studentRepository,PaymentRepository paymentRepository) {
		this.paymentRepository=paymentRepository;
		this.studentRepository=studentRepository;
		this.paymentService=paymentService;
	}
	
	@GetMapping(path="/payments")
	public List<Payment>allPayment(){
		return paymentRepository.findAll();
		
	}
	@GetMapping(path="/students/{code}/payments")
	public List<Payment>paymentByStudent(@PathVariable String code){
		return paymentRepository.findByStudentCode(code);
		
	}
	@GetMapping(path="/payments/byStatus")
	public List<Payment>paymentByStatus(@RequestParam PaymentStatus  status){
		return paymentRepository.findByStatus(status);
		
	}
	@GetMapping(path="/payments/byType")
	public List<Payment>paymentByType(@RequestParam PaymentType  type){
		return paymentRepository.findByType(type);
		
	}
	@GetMapping(path="/payment/{id}")
	public Payment getPaymentById(@PathVariable Long id) {
		return paymentRepository.findById(id).get();
	}
	@GetMapping("/students")
	public List<Student> allStudents(){
		return studentRepository.findAll();
		
	}
	@GetMapping(path="/student/{code}")
	public Student getStudentByCode(@PathVariable String code) {
		return studentRepository.findByCode(code);
		}
	
	@GetMapping(path="/studentByProgramId")
	public List<Student> getStudentByProgramId(@RequestParam String programId) {
		return studentRepository.findByProgramId(programId);
		}
	@PutMapping("/payments/{id}")
	public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id) {
		return paymentService.updatePaymentStatus(status, id);
	}
	@PostMapping(path="payments",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public Payment savePayment(@RequestParam MultipartFile file, 
			LocalDate date, 
			double amount,
			PaymentType type, 
			String studentCode) throws IOException {
		return this.paymentService.savePayment(file, date, amount, type, studentCode);
	
	}
	
	@GetMapping(path="/payments/{paymentId}",produces=MediaType.APPLICATION_PDF_VALUE)
	public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
		
		return paymentService.getPaymentFile(paymentId);
		
	}
}
