package com.crd.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Show {

	@Id
	private int id;

	@Column
	private String title;

	@Column
	private String about;
	
	@Column 
	private int episodes;

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

	public int getEpisodes() {
		return episodes;
	}

	public void setEpisodes(int episodes) {
		this.episodes = episodes;
	}

}
