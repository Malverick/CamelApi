package com.crd.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Movie {
	@SuppressWarnings("all")
	public Movie() {
		this.title = title;
		this.about = about;
	}

	@Id
	private int id;

	@Column
	private String title;

	@Column
	private String about;

}
