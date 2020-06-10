package com.crd.example.routes;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RouteToDB extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	@Override
	public void configure() throws Exception {
		//get start
		from("direct:start")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}")
		.process(buildResponseProcessor);};
//		from("direct:post").process(exchange -> exchange.getIn().setBody("Hello"));
		
//		//post start
//		from("direct:postMovie")
//		.process(buildRequestProcessor)
//		.to("jdbc:dataSource").log("body = ${body}")
//		.process(buildCreateProcessor);
//		}
	
//	//post
//		Processor buildCreateProcessor = exchange -> {
//			Movie movie = exchange.getIn().getBody(Movie.class);
//			log.info(movie.toString());
//			exchange.getIn().setBody(movie);
//	};
	//get
		Processor buildResponseProcessor = exchange -> {
			Movie movie = exchange.getIn().getBody(Movie.class);
//			log.info(movie.toString());
			exchange.getIn().setBody(movie);
	};
		final Processor buildRequestProcessor = exchange -> {
			String param = exchange.getIn().getHeader("title").toString();
			log.info("title param = " + param);
			String selectQuery = "SELECT * FROM movies WHERE \"Title\" = " + "'" +param +"'";
			exchange.getIn().setBody(selectQuery);
	};

}
