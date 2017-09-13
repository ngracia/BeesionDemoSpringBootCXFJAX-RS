package com.beesion.demo;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.beesion.servicios.ServiciosBeesion;
import com.beesion.servicios.ServiciosBeesionImpl;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer  {

	@Autowired
	private Bus bus;
	


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(DemoApplication.class);
	}
	
	@Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        endpoint.setServiceBeans(Arrays.<Object>asList(new ServiciosBeesionImpl()));
        endpoint.setProviders(Arrays.asList(jacksonJaxbJsonProvider()));
        return endpoint.create();
    }
	
	@Bean
	public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
		return new JacksonJaxbJsonProvider();
	}
	 
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Inicio listo");
	}
}
