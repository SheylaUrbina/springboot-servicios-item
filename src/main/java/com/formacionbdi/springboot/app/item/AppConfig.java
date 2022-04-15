package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	//ver balanceo de carga utilizando rest template y ribbon
	@Bean("clienteRest")
	@LoadBalanced //con esta notación va utilizar ribbon para balanceo de carga
	//y resttemplate ppor debajo busca la mejor instancia disponible
	//esto es para configurar RestTemplate con ribbon
	
	//RestTemplate: cliente para trabajar con apirest, un cliente HTTP para
	//poder acceder a recursos que estan en otros microservicios.
	
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

}
