package com.crd.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
//@Table(value="movies")
public class Movie {
	
	@Id
	private int id;
	
	@Column
	private String title;
	
	@Column
	private String about;

}
