package com.crd.example.endpoint;

import com.crd.example.routes.DeleteRoutes;
import com.crd.example.routes.GetRouting;
import com.crd.example.routes.PostRouting;
import com.crd.example.routes.UpdateRoutes;
import com.crd.example.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.camel.test.junit4.CamelTestSupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieEndpointUnitTests extends CamelTestSupport  {
	
	@InjectMocks
	MovieEndpoint movieEndpoint;
	
	@Mock
	GetRouting getRouting;
	DeleteRoutes deleteRouting;
	PostRouting postRouting;
	UpdateRoutes updateRouting;
	
	@Test
	public void getMovieTest() {
		movieEndpoint
	}
}
