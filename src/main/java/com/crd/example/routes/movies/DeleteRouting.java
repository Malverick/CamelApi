package com.crd.example.routes.movies;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeleteRouting extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	@Override
	public void configure() throws Exception {
		//delete start
		from("direct:delete")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};
		
		
		final Processor buildRequestProcessor = exchange -> {
			String param = exchange.getIn().getHeader("title").toString();
			log.info("title param = " + param);
			String deleteQuery = "DELETE FROM movies WHERE \"Title\" = " + "'" +param +"';";
			exchange.getIn().setBody(deleteQuery);
	};
}
