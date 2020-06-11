package com.crd.example.routes;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoutes  extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	@Override
	public void configure() throws Exception {
		//update start
		from("direct:update")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};
		
		
		final Processor buildRequestProcessor = exchange -> {
			String param = exchange.getIn().getHeader("title").toString();
			log.info("title param = " + param);
//			String selectQuery = "SELECT * FROM movies WHERE \"Title\" = " + "'" +param +"'";
//			exchange.getIn().setBody(selectQuery);
	};

}
