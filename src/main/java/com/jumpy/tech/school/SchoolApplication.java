package com.jumpy.tech.school;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jumpy.tech.school.entites.Payment;
import com.jumpy.tech.school.entites.PaymentStatus;
import com.jumpy.tech.school.entites.PaymentType;
import com.jumpy.tech.school.entites.Student;
import com.jumpy.tech.school.repository.PaymentRepository;
import com.jumpy.tech.school.repository.StudentRepository;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository stuRepo,PaymentRepository payRepo) {
		return args->{
			
			stuRepo.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Gedeon Rodrigue")
					.lastName("POUDJOUM LEUTCHO")
					.code("24A226")
					.programId("LICINFO3")
					.build());
			
			stuRepo.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Stéphanie Ange")
					.lastName("MAFOGANG WANDJI")
					.code("24A256")
					.programId("LICINFO3")
					.build());
			stuRepo.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("RUTH SYLVIANE")
					.lastName("CHAMENI LEUTCHO")
					.code("24A225")
					.programId("LICBANK2")
					.build());
			stuRepo.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("NATHAN MICHEE")
					.lastName("DEUMENI LEUTCHO")
					.code("24A2668")
					.programId("LICINFO4")
					.build());
			
			
			PaymentType[] paymentTypes=PaymentType.values();
			Random random=new Random();
			
			stuRepo.findAll().forEach(st->{
				for(int i=0;i<5;i++) {
					int index=random.nextInt(paymentTypes.length);
					Payment payment=Payment.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();		
					
					payRepo.save(payment);
				}
			});
		};
		
	}

}
