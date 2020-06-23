package com.crd.example.routes.shows;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crd.example.model.Show;

@Component
public class PostRouting extends RouteBuilder{

	@Autowired DataSource dataSource;
	
	@Override
	public void configure() throws Exception {
		//Post start
		from("direct:posts")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};

		final Processor buildRequestProcessor = exchange -> {
			Show show = exchange.getIn().getBody(Show.class);
			log.info("title param = " + show.getTitle());
			log.info("about param = " + show.getAbout());
			String insertQuery = "INSERT INTO shows (\"Title\", \"About\") VALUES(" + "'" + show.getTitle() + "', '" + show.getAbout() + "');";
			exchange.getIn().setBody(insertQuery);
	};
}
