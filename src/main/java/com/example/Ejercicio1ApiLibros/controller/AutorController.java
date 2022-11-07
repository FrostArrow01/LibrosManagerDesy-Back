package com.example.Ejercicio1ApiLibros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ejercicio1ApiLibros.dto.AutorSlimDto;
import com.example.Ejercicio1ApiLibros.entities.Autor;
import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.example.Ejercicio1ApiLibros.mapper.AutorMapper;
import com.example.Ejercicio1ApiLibros.services.AutorService;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/autor")
@Tag(name="Controlador de Autor")
public class AutorController {
	@Autowired
	private AutorService autorS;
	
	@Autowired
	private AutorMapper autorMapper;
	
//		Buscar todos los autores
		@GetMapping("/all")
		public MessageResponseDto<List<Autor>> findAll() {
			return autorS.findAllAutor();
		}
		
//		Buscar todos los autorSlimDto
		@GetMapping("/allDto")
		public MessageResponseDto<List<AutorSlimDto>> findAllDto() {
			return autorS.findAllAutorSlimDto();
		}
		
//		Buscar autor por id
		@GetMapping("/getById/{id}")
		public MessageResponseDto<Autor> findById(@PathVariable String id) {//el @PathVariable indica que ese parametro esta en la url
			return autorS.findByIdAutor(id);
			 
		}
		
//		Crear autor en base de datos
		@PostMapping("/add")
		public MessageResponseDto<String> create(@RequestBody Autor autor) { // el @RequestBody indica que tiene que coger la info del cuerpo de la request
			return autorS.createAutor(autor);
		}
			
//		Actualizar autor existente
		@PutMapping("/editar/{id}")
		public MessageResponseDto<String> update(@RequestBody Autor autor, @PathVariable String id) {
			return autorS.updateAutor(autor, id);
		}
		
//		Borrar autor en base de datos
		@DeleteMapping("/delete/{id}")
		public MessageResponseDto<String> delete(@PathVariable String id){
			return autorS.deleteAutor(id);
		}
		
//		Borrar autor en base de datos
		@PostMapping("/deleteM")
		public MessageResponseDto<String> delete(@RequestBody ArrayList<Autor> autores){
			return autorS.deleteMultipleLibros(autores);
		}
		
		
}
