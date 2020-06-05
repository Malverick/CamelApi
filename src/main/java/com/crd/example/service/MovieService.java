package com.crd.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crd.example.model.Movie;
import com.crd.example.routes.MovieRoute;

public class MovieService {

	private MovieRoute movieRoute;

	private MovieService(@Autowired MovieRoute movieRoute) {
		this.movieRoute = movieRoute;
	}

	public List<Movie> getAllMovies() throws Exception {
		return (List<Movie>) movieRoute.findAll();
	}

}
