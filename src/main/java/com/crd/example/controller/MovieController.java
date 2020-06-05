package com.crd.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.example.model.Movie;
import com.crd.example.service.MovieService;

@RestController
public class MovieController {

	private MovieService movieService;

	private MovieController(@Autowired MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping(path = "/get/all")
	public ResponseEntity<List<Movie>> getMovies() {
		try {
			return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.notFound().build();
	}
}
