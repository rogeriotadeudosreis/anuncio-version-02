package com.rogerioreis.anuncio02.profile.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ConfigProfileDev implements ConfigProfile {

	public ConfigProfileDev() {
		System.out.println("/////////////////////////Configurações de dev.....");

	}

}
