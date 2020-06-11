package com.crd.example.routes;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

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
			String param1 = exchange.getIn().getHeader("title").toString();
			String param2 = exchange.getIn().getHeader("about").toString();
			log.info("title param = " + param1);
			log.info("about param = " + param2);
			String insertQuery = "INSERT INTO movies (title, about) VALUES(" + "'" + param1 + "', '" + param2 + "');";
			exchange.getIn().setBody(insertQuery);
	};

}
