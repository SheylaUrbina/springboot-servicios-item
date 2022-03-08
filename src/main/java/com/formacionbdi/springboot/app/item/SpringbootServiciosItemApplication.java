package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//comunicacion de los servicios
@EnableCircuitBreaker// habilitado hystrix, hilos separados de ms, envuelve ribbon para manejar
//tolerancia a fallos,manejo de latencia y time out

@EnableEurekaClient
//@RibbonClient(name = "servicio-productos") el nombre de client feign dado en clase ItemServiceFeign, ademas se quita porque se impletara EUREKA y ya trae un balanceador de carga
@EnableFeignClients
@SpringBootApplication
public class SpringbootServiciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosItemApplication.class, args);
	}

}
