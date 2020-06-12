package com.crd.example.routes;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;

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
			Movie movie = exchange.getIn().getBody(Movie.class);
			String idParam = exchange.getIn().getHeader("id").toString();
			log.info("id param = " + idParam);
			log.info("new title param = " + movie.getTitle());
			log.info("new about param = " + movie.getAbout());
			String updateQuery = "UPDATE movies SET \"Title\" = " + "'" + movie.getTitle() +"', " + "\"About\" = '" + movie.getAbout() +"' WHERE \"Id\" = " + idParam + ";";
			exchange.getIn().setBody(updateQuery);
	};
}
