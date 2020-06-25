package com.crd.example.routes.shows;

import javax.sql.DataSource;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crd.example.model.Show;

@Component
public class UpdateRoutingS extends RouteBuilder{

	@Autowired DataSource dataSource;
	
	@Override
	public void configure() throws Exception {
		//update start
		from("direct:updates")
		.process(buildRequestProcessor)
		.to("jdbc:dataSource").log("body = ${body}");};
		
		
		final Processor buildRequestProcessor = exchange -> {
			Show show = exchange.getIn().getBody(Show.class);
			String idParam = exchange.getIn().getHeader("id").toString();
			log.info("id param = " + idParam);
			log.info("new title param = " + show.getTitle());
			log.info("new about param = " + show.getAbout());
			String updateQuery = "UPDATE movies SET \"Title\" = " + "'" + show.getTitle() +"', " + "\"About\" = '" + show.getAbout() +"' WHERE \"Id\" = " + idParam + ";";
			exchange.getIn().setBody(updateQuery);
	};
}
