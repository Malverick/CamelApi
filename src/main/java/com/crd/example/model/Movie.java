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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	

}
