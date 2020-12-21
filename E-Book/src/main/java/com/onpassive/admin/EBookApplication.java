package com.onpassive.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EBookApplication {
	
//	private static final Logger LOGGER=LoggerFactory.getLogger(EBookApplication.class);

	public static void main(String[] args) {
//		LOGGER.debug("main method start");
		SpringApplication.run(EBookApplication.class, args);
//		LOGGER.debug("main method end");
	}
	
	@Bean
	public Docket productApi() {
	return new Docket(DocumentationType.SWAGGER_2).select()
	.apis(RequestHandlerSelectors.basePackage("com.onpassive.admin"
	)).build();
	}

}
