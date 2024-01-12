package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//comunicacion de los servicios
//@EnableCircuitBreaker  1. habilitado hystrix, hilos separados de ms, envuelve ribbon para manejar tolerancia a fallos, time out
//2. @EnableCircuitBreaker Se quita cuando se integra el Resilience4j
//tolerancia a fallos,manejo de latencia y time out

@EnableEurekaClient
//@RibbonClient(name = "servicio-productos") el nombre de client feign dado en clase ItemServiceFeign, ademas se quita porque se impletara EUREKA y ya trae un balanceador de carga.
//las configuraciones para usar el ribbon se hacen en el archivo application.properties
// de este maner #NombreMicroServicioAConfigurar.ribbon.listOfService=localhost:8001,localhost:9001
@EnableFeignClients //habilita clientes feign que esten habilitados dentro del proyecto,
//permite inyectar estos clientes en nuestros controladores u otros
//componentes de spring
@SpringBootApplication
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootServiciosItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosItemApplication.class, args);
	}

}
