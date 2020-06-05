package com.crd.example;

import javax.sql.DataSource;

import org.apache.camel.main.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.apache.commons.dbcp.BasicDataSource;

@SpringBootApplication
@ComponentScan(basePackages = "com.crd.example.routes")
public class ExampleApplication {

//	private Main main;
	
	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

//	 public void boot() throws Exception {
//	        // create a Main instance
//	        main = new Main();
//	        // enable hangup support so you can press ctrl + c to terminate the JVM
//	        main.enableHangupSupport();
//
//	        String url = "jdbc:postgresql://localhost:5432/mydb2";
//	        DataSource dataSource = setupDataSource(url);
//	        // run until you terminate the JVM
//	        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
//	        main.run();
//	    }
//
//
//	    private DataSource setupDataSource(String connectURI) {
//	        BasicDataSource ds = new BasicDataSource();
//	        ds.setUsername("user");
//	        ds.setPassword("password");
//	        ds.setUrl(connectURI);
//	        return ds;
//	    }
	
}