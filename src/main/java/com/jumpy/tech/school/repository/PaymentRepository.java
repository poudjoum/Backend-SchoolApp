package com.jumpy.tech.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jumpy.tech.school.entites.Payment;
import com.jumpy.tech.school.entites.PaymentStatus;
import com.jumpy.tech.school.entites.PaymentType;

public interface PaymentRepository extends JpaRepository<Payment,Long>{
	
	List<Payment> findByStudentCode(String code);
	List<Payment> findByStatus(PaymentStatus status);
	List<Payment> findByType(PaymentType type);

}
