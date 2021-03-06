package com.crd.example.endpoint;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Component;

import com.crd.example.model.Movie;
import com.crd.example.model.Show;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

@Component
public class ShowEndpoint extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component("servlet")
			.bindingMode(RestBindingMode.json);
		
		rest()
			.path("/shows")
			.get("/getall")
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.outType(List.class)
			.to("direct:getalls");
		
	
		rest()
			.path("/shows")
			.get("/get")
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.param().name("id").type(RestParamType.query).dataType("int").endParam()
			.param().name("title").type(RestParamType.query).dataType("String").endParam()
			.param().name("about").type(RestParamType.query).dataType("String").endParam()
			.param().name("episodes").type(RestParamType.query).dataType("int").endParam()
			.outType(Show.class)
			.to("direct:gets");
		
		rest()
			.path("/shows")
			.post("/add")
			.type(Show.class)
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.outType(Show.class)
			.to("direct:posts");
		
		rest()
			.path("/shows")
			.post("/delete")
			.param().name("id").type(RestParamType.query).dataType("int").endParam()
			.param().name("title").type(RestParamType.query).dataType("String").endParam()
			.param().name("about").type(RestParamType.query).dataType("String").endParam()
			.param().name("episodes").type(RestParamType.query).dataType("int").endParam()
			.outType(Show.class)
			.to("direct:deletes");
		
		rest()
			.path("/shows")
			.put("/update")
			.type(Movie.class)
			.param().name("id").type(RestParamType.query).dataType("int").endParam()
			.param().name("title").type(RestParamType.query).dataType("String").endParam()
			.param().name("about").type(RestParamType.query).dataType("String").endParam()
			.param().name("episodes").type(RestParamType.query).dataType("int").endParam()
			.produces(ContentType.APPLICATION_JSON.getMimeType())
			.outType(Show.class)
			.to("direct:updates");
	}
		
}