package com.crd.example.routes.shows;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRouting extends RouteBuilder{

	@Autowired DataSource dataSource;
	
	@Override
	public void configure() throws Exception {
		//get start
		from("direct:gets")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};

		final Processor buildRequestProcessor = exchange -> {
			String param = exchange.getIn().getHeader("title").toString();
			log.info("title param = " + param);
			String selectQuery = "SELECT * FROM shows WHERE \"Title\" = " + "'" +param +"';";
			exchange.getIn().setBody(selectQuery);
	};
}
