package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	@Autowired
	@Qualifier("serviceFeign")
	//@Autowired
	//@Qualifier("serviceRestTemplate") //Qualifier, sirven para determinar el componente rest a utilizar y principal
	private ItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod= "metodoAlternativo")//si hubo un error en el servicio a consultar 
	//y usamos hixtrix podemos decirle que haga otra cosa, esto sería manejo de fallos
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id,@PathVariable Integer cantidad){
		return itemService.findById(id,cantidad);
	}
	
	//en caso de error en metodo de ver se dispara esta funcion
	//llamado camino alternativo en caso de errores
	public Item metodoAlternativo(Long id,Integer cantidad) {
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
