package com.crd.example.endpoint;

import org.apache.http.entity.ContentType;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MovieEndpoint extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		restConfiguration()
			.component("servlet");
		
	     rest("/app").get("/hello")
           .route()
           .to("direct:hello").endRest();

	    from("direct:hello")
        	.log(LoggingLevel.INFO, "Hello World")
        	.transform().simple("Hello World");
		
		rest()
			.path("/get")
			.get("/movies")
			.bindingMode(RestBindingMode.json)
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.param().name("id").type(RestParamType.query).dataType("int").endParam()
			.param().name("title").type(RestParamType.query).dataType("String").endParam()
			.param().name("about").type(RestParamType.query).dataType("String").endParam()
			.outType(Movie.class)
			.to("direct:get");
		
		rest()
			.path("/post")
			.post("/movies")
			.type(Movie.class)
			.bindingMode(RestBindingMode.json)
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.param().name("title").type(RestParamType.query).dataType("string").endParam()
			.param().name("about").type(RestParamType.query).dataType("String").endParam()
			.param().name("id").type(RestParamType.query).dataType("int").endParam()
			.outType(Movie.class)
			.to("direct:post");
	}
}
