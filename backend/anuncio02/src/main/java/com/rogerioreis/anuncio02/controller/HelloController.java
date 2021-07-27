package com.rogerioreis.anuncio02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "Página de teste para autenticação do usuário\n"
				+ "Se vc estiver vendo esta página, usuário logado com sucesso.";
	}

}
