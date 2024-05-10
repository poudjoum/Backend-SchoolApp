package com.jumpy.tech.school.response;



import java.sql.Blob;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StudentResponse {
	 private String id;
	 private String firstName;
	 private String lastName;
	 private String code;
	 private String programId;
	 private String photo;

	public StudentResponse(String id,String firstName,String lastName,String code,String programId,Blob blob) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.code=code;
		this.programId=programId;
		 this.photo = (photo != null) ? Base64.encodeBase64String(photo.getBytes()) : null;
		
	}
	public StudentResponse(String id, String firstName,String lastName,String code,String programId) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.code=code;
		this.programId=programId;
		
	}
}
