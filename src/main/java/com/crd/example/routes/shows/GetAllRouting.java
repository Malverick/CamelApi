package com.crd.example.routes.shows;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllRouting extends RouteBuilder {
	
	@Autowired DataSource dataSource;
	
	public void configure() throws Exception {
		from("direct:getalls")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};

		final Processor buildRequestProcessor = exchange -> {
			String selectQuery = "SELECT * FROM shows;";
			exchange.getIn().setBody(selectQuery);
	};
}
