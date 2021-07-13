package com.rogerioreis.anuncio02.profile.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rogerioreis.anuncio02.profile.config.ConfigProfile;

@Component
public class TesteConfig {
	
	@Autowired
	public TesteConfig(ConfigProfile configProfile) {
		
		System.out.println("/////////////////////////Config class = " + configProfile.getClass().getName());
	}

}
