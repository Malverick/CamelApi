package com.crd.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crd.example.model.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieSmokeTest {

	@Autowired
	public Movie movie;

	@Test
	public void exampleApplicationAPIApplicationSmokeTest() {
		assertThat(this.movie).isNotNull();
	}
}
