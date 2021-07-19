package com.rogerioreis.anuncio02.profile.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class ConfigProfileTest implements ConfigProfile {

	public ConfigProfileTest() {
		System.out.println("/////////////////////////Configurações de test.....");
	}
	
}
