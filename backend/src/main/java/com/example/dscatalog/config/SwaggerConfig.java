package com.example.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration		/* informa ao spring que se trata de uma notação de configuração */
@EnableSwagger2  /* habilita o processamento do swagger */
public class SwaggerConfig {
	
	@Bean   /* anotação que informa que se trata de um componente do tipo Docket */
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build();
		}

	/*  RestController.class -> informa ao swagger que só vai escanear os endpoint @RestController ignorando por exemplo
	 * os endpoints oauth 
	 *	PathSectors.any() -> informa para selecionar qualquer path dos tipos @RestController 
	 * 
	 * build() ->  efetua a construção    */

}
