package com.crd.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crd.example.endpoint.MovieEndpoint;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieEndpointSmokeTest {

	@Autowired
	public MovieEndpoint movieEndpoint;
	
	@Test
	public void exampleApplicationAPIApplicationSmokeTest() {
		assertThat(this.movieEndpoint).isNotNull();
	}
}
