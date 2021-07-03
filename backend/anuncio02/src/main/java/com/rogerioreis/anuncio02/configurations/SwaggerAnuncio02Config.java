package com.rogerioreis.anuncio02.configurations;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAnuncio02Config {
	
	String title = "API Rest com SpringBoot";
	String description = "API anúncio de imóveis";
	String version = "1.0";
	String terms = "Terms of Service";
	String name = "Rogério Reis";
	String url = "https://github.com/rogeriotadeudosreis";
	String email = "rogerio.rtr46@gmail.com";
	String license = "Apache License Version 2.0";
	String licenseUrl = "https://www.apache.org/licesen.html";
	
	
	@Bean
	public Docket api()	{
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rogerioreis.anuncio02"))
				.paths(PathSelectors.ant("/user/**"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(title, description, version, terms, 
				new Contact("Rogério Reis", "https://github.com/rogeriotadeudosreis", "rogerio.rtr46@gmail.com"),
				license,
				licenseUrl,
				new ArrayList<VendorExtension>());

		return apiInfo;
	}
}
