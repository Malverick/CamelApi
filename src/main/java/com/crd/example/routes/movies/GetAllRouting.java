package com.crd.example.routes.movies;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GetAllRouting extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	public void configure() throws Exception {
		from("direct:getall")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};

		final Processor buildRequestProcessor = exchange -> {
			String selectQuery = "SELECT * FROM movies;";
			exchange.getIn().setBody(selectQuery);
	};
}
