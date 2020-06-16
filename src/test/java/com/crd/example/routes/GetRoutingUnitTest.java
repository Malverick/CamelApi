package com.crd.example.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.catalina.startup.ContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@SuppressWarnings("deprecation")
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ContextConfig.class }, loader = CamelSpringDelegatingTestContextLoader.class)
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
}
