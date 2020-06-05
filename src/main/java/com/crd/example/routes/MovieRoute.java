package com.crd.example.routes;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;

@Component
public class MovieRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public List<Movie> findAll() throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("localhost:5432/mydb2" + "?get=true");}
		});
		return (List<Movie>) camelContext;
		
	}
}
