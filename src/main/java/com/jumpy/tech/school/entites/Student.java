package com.jumpy.tech.school.entites;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder


public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String code;
	private String programId;
	@Lob
	private Blob photo;
	

}
