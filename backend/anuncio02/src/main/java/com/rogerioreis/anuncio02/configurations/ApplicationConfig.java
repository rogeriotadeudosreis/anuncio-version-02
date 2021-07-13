package com.rogerioreis.anuncio02.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Esta classe faz o mapeamento entre entidades e entidades DTO.
 */
@Configuration
public class ApplicationConfig {

	/*
	 * https://amydegregorio.com/2018/12/16/modelmapper-in-spring-boot-no-starter/
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelMapper;
	}

}
