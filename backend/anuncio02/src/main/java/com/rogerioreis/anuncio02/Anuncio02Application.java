package com.rogerioreis.anuncio02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class Anuncio02Application {

	public static void main(String[] args) {
		SpringApplication.run(Anuncio02Application.class, args);
		System.out.println("Senha do primeiro usuário: ---> " + new BCryptPasswordEncoder().encode("123456"));
	}

}
