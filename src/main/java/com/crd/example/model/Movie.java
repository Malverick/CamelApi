package com.crd.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Movie {
	
	@Id
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String about;

}
