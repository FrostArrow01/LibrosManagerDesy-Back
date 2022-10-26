package com.example.Ejercicio1ApiLibros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejercicio1ApiLibros.entities.Categoria;
import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.example.Ejercicio1ApiLibros.repositories.CategoriaRepository;
import com.example.Ejercicio1ApiLibros.services.CategoriaService;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Tag(name="Controlador de Categoría")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaS;
	
	//Buscar todas las categoria
		@GetMapping("/all")
		public MessageResponseDto<List<Categoria>> findAll() {
			return categoriaS.findAllCategoria();
		}
		
//		Buscar categoria por id
		@GetMapping("/getById/{id}")
		public MessageResponseDto<Categoria> findById(@PathVariable Integer id) {//el @PathVariable indica que ese parametro esta en la url
			return categoriaS.findCategoriabyId(id);
			 
		}
		
//		Crear categoria en base de datos
		@PostMapping("/add")
		public MessageResponseDto<String> create(@RequestBody Categoria categoria, @RequestHeader HttpHeaders headers) { // el @RequestBody indica que tiene que coger la info del cuerpo de la request
			System.out.println(headers.get("User-Agent")); //con HttpHeaders e imprimiendo esta línea sabemos desde donde se hace la peticion
			return categoriaS.addCategoria(categoria);
		}
		
//		Actualizar categoria existente
		@PutMapping("/editar/{id}")
		public MessageResponseDto<String> update(@RequestBody Categoria cuerpo, @PathVariable Integer id) {
			return categoriaS.updateCategoria(id, cuerpo);
		}
		
//		Borrar categoria en base de datos
		@DeleteMapping("/delete/{id}")
		public  MessageResponseDto<String> delete(@PathVariable Integer id){
			return categoriaS.deleteCategoriabyId(id);
		}
		
//		Borrar libro en base de datos
		@PostMapping("/deleteM")
		public MessageResponseDto<String> delete(@RequestBody ArrayList<Categoria> categorias){
			return categoriaS.deleteMultipleLibros(categorias);
		}
		
		
		
}
