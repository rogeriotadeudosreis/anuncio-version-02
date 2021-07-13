package com.rogerioreis.anuncio02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Anuncio02Application {

	public static void main(String[] args) {
		SpringApplication.run(Anuncio02Application.class, args);
//		System.out.println("Senha do primeiro usuário: ---> " + new BCryptPasswordEncoder().encode("123456"));
	}
	
	@RequestMapping("/home")
	public String hello() {
		return "Hello buddy! Aqui é apenas um endproint para testes de rotas autorizadas";
	}

}
