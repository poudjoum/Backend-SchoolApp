package com.jumpy.tech.school.dtos;

import java.time.LocalDate;

import com.jumpy.tech.school.entites.PaymentStatus;
import com.jumpy.tech.school.entites.PaymentType;
import com.jumpy.tech.school.entites.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class PaymentDTO {
	
	private Long id;
	private LocalDate date;
	private double amount;
	private PaymentType type;
	private PaymentStatus status;
	private Student student;

}
