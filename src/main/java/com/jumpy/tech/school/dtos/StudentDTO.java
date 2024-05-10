package com.jumpy.tech.school.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO {

	private String id;
	private String firstName;
	private String lastName;

	private String code;
	private String programId;
	 
	private String photo;
}
