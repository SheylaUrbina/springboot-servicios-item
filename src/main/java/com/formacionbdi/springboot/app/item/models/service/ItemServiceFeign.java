package com.formacionbdi.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;

@Service("serviceFeign")
//@Primary --usado cuando hay mas de un cliente rest esto indica el principal a tomar en cuenta
//esto hará que en itemcontroller de la clase ItemController con el autowired, se inyecte automaticamente este ItemService implementacion
//y no el itemservice de resttemplate
//por otro lado al agregar nombre al Service, tambien se manda a llamar al itemcontroller
//para hacerlo de forma mas explicita
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map( p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
	
		return new Item(clienteFeign.detalle(id),cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
		
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
		
	}

}
