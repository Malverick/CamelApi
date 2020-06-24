package com.crd.example.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
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
import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = com.crd.example.ExampleApplicationTests.class)
@EnableRouteCoverage
@DisableJmx
public class GetRoutingUnitTest {
	@Autowired
	private CamelContext camelContext;

	@EndpointInject(uri = "mock:log:foo")
	private MockEndpoint mock;

	@EndpointInject(uri = "mock:mocked-endpoint")
	private MockEndpoint mockRequestEndpoint;

	@Value("${json.placeholder.base.url}")
	private String jsonPlaceHolderString;

	@Before
	public void mockEndpoints() throws Exception {
		AdviceWithRouteBuilder mocklSolr = new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				weaveAddLast().to("mock:log:foo");
				interceptSendToEndpoint(String.format("http4:%s/posts*", jsonPlaceHolderString))
				.when(header(Exchange.HTTP_PATH).isEqualTo("1"))
				.process(exchange -> System.out.println("Intercepted ROute endpoint"))
				.skipSendToOriginalEndpoint()
				.to("mock:mocked-endpoint");
			}
		
		};
		mockRequestEndpoint.whenAnyExchangeReceived(exchange -> {
			exchange.getIn().setBody("Hello, World.");
		});
		camelContext.getRouteDefinition(PostRequestRoute.ROUTE_ID).adviceWith(camelContext, mockSolr);
}
	@Test
	public void shouldSayFoo() throws Exception {
		mock.expectedBodiesReceived("Hello, World.");
		NotifyBuilder notify = new NotifyBuilder(camelContext).whenDone(1).create();
        assertTrue(notify.matches(10, TimeUnit.SECONDS));
        mock.assertIsSatisfied();
	}
}
