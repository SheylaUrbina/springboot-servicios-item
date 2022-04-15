package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@RestController
public class ItemController {
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	@Qualifier("serviceFeign") //aqui le indicmos cual service usara si el resttemplate o el feign
	//@Autowired
	//@Qualifier("serviceRestTemplate") //Qualifier, sirven para determinar el componente rest a utilizar y principal
	private ItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar(@RequestParam(name="nombre", required= false) String nombre, @RequestHeader(name="token-request", required= false) String token){
		System.out.println("nombre: "+nombre);
		System.out.println("token: "+token);
		
		return itemService.findAll();
	}
	
	//Se quita el @HystrixCommand cuando se integra el resilience4j 
	//@HystrixCommand(fallbackMethod= "metodoAlternativo")//si hubo un error en el servicio a consultar 
	//y usamos hixtrix podemos decirle que haga otra cosa, esto sería manejo de fallos
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id,@PathVariable Integer cantidad){
		return cbFactory.create("items")
				.run(()-> itemService.findById(id,cantidad), e -> metodoAlternativo(id, cantidad, e));
	}
	
	//en caso de error en metodo de ver se dispara esta funcion
	//llamado camino alternativo en caso de errores
	public Item metodoAlternativo(Long id,Integer cantidad, Throwable e) {
		
		logger.info(e.getMessage());
		
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Ropa Usada");
		
		item.setProducto(producto);
		return item;
	}
	
	@GetMapping("/prueba")
	public String prueba() {
		return "estamos en servicio";
	}
}
