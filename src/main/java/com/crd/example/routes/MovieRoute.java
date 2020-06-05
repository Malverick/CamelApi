package com.crd.example.routes;

import java.util.List;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;

@Component
public class MovieRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		from("direct:start")
		.process(buildrRequestProcessor)
		.to("jdbc:postgresql://localhost:5432/mydb2");
	}
	final Processor buildrRequestProcessor = exchange -> {
		String param = exchange.getIn().getHeader("name").toString();
		log.info("title param = " + param);
		String selectQuery = "SELECT * FROM movies WHERE name = " + param;
		exchange.getIn().setBody(selectQuery);
	};

}
