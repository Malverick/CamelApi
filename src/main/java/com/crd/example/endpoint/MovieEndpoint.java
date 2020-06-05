package com.crd.example.endpoint;

import java.util.List;

import org.apache.http.entity.ContentType;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.example.model.Movie;
import com.crd.example.routes.MovieRoute;

@Component
public class MovieEndpoint extends RouteBuilder{

	private MovieRoute movieRoute;

	private MovieEndpoint(@Autowired MovieRoute MovieRoute) {
		this.movieRoute = movieRoute;
	}

	public void configure() throws Exception {
		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json);
		
		rest()
		.path("/path")
		.produces(ContentType.APPLICATION_JSON.getMimeType())
		.get("/movies")
		.param().name("title").type(RestParamType.query).dataType("string").endParam()
		.param().name("id").type(RestParamType.query).dataType("int").endParam()
		.outType(Movie.class)
		.to("direct:start");
		
		rest()
		.path("/getById")
		.produces(ContentType.APPLICATION_JSON.getMimeType())
		.get("/movies")
		.param().name("title").type(RestParamType.query).dataType("string").endParam()
		.param().name("id").type(RestParamType.query).dataType("int").endParam()
		.outType(Movie.class)
		.to("direct:getById");
	}
}
