package com.crd.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crd.example.routes.movies.DeleteRouting;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteRoutingSmokeTest {
	@Autowired
	public DeleteRouting deleteRouting;

	@Test
	public void exampleApplicationAPIApplicationSmokeTest() {
		assertThat(this.deleteRouting).isNotNull();
	}
}
