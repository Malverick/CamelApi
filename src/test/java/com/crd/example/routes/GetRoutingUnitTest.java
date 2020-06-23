package com.crd.example.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.EnableRouteCoverage;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.catalina.startup.ContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.EnableRouteCoverage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = com.crd.example.ExampleApplicationTests.class)
@EnableRouteCoverage
@DisableJmx
public class GetRoutingUnitTest extends AbstractJUnit4SpringContextTests {

	@EndpointInject("mock:get")
	protected MockEndpoint resultEndpoint;

	@Produce("direct:get")
	protected ProducerTemplate template;

	@DirtiesContext
	@Test
	public void getRoutingTest() throws Exception {
		String expectedBody= "[\r\n" + 
				"    {\r\n" + 
				"        \"Id\": 1,\r\n" + 
				"        \"Title\": \"Shrek\",\r\n" + 
				"        \"About\": \"stuff\\n\"\r\n" + 
				"    }\r\n" + 
				"]";
				resultEndpoint.expectedBodiesReceived(expectedBody);
				template.sendBodyAndHeader(expectedBody, "Shrek", "Shrek");
				resultEndpoint.assertIsSatisfied();
	}
	
    @Configuration
    public static class ContextConfig {
        @Bean
        public RouteBuilder route() {
            return new RouteBuilder() {
                public void configure() {
                    from("direct:start").filter(header("Shrek").isEqualTo("Shrek")).to("mock:get");
                }
            };
        }
    }
}
