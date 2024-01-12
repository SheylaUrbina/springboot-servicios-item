package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.models.Producto;

//Esto s un cliente feign y se define a que ms nos queremos conectar
//atraves del atributo name del configurado en application.properties
@FeignClient(name="servicio-productos")
//@FeignClient(name="servicio-productos",url="localhost:8001") --sin ribbon
//con ribbon se quita el url y se configura en el archivo de configuracion de ribbon en este caso application.properties,
//con el obj de desacoplar la direccion fisica

public interface ProductoClienteRest {
	
	//solo se agregan los metodos sin la implementacion
	//ya que es una interfaz
	@GetMapping("/listar")
	public List<Producto> listar();
		
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Producto crear(@RequestBody Producto producto) ;
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
}
