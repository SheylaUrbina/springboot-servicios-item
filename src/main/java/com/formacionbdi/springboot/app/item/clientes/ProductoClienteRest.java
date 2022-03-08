package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.models.Producto;


@FeignClient(name="servicio-productos")
//@FeignClient(name="servicio-productos",url="localhost:8001") --sin ribbon
//con ribbon se quita el url y se configura en el archivo de configuracion de ribbon en este caso application.properties,
//con el obj de desacoplar la direccion fisica

public interface ProductoClienteRest {
	
	@GetMapping("/listar")
	public List<Producto> listar();
		
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
}
