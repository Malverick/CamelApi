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
public class PostRouting extends RouteBuilder {
	
	@Autowired
	DataSource dataSource;

	@Override
	public void configure() throws Exception {
		//Post start
		from("direct:post")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};

		final Processor buildRequestProcessor = exchange -> {
			Movie movie = exchange.getIn().getBody(Movie.class);
			log.info("title param = " + movie.getTitle());
			log.info("about param = " + movie.getAbout());
			String insertQuery = "INSERT INTO movies (\"Title\", \"About\") VALUES(" + "'" + movie.getTitle() + "', '" + movie.getAbout() + "');";
			exchange.getIn().setBody(insertQuery);
	};

}
