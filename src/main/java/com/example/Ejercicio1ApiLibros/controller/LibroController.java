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
import org.springframework.web.servlet.ModelAndView;

import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.example.Ejercicio1ApiLibros.repositories.LibroRepository;
import com.example.Ejercicio1ApiLibros.services.LibroService;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/libro")
@Tag(name="Controlador de Libro")
public class LibroController {
	
	@Autowired
	private LibroService libroS;
	
	
	//Buscar todos los libros
		@GetMapping("/all")
		public MessageResponseDto<List<Libro>> findAll() {
			return libroS.findAllLibro();
		}
		
//		Buscar libro por id
		@GetMapping("/getById/{id}")
		public MessageResponseDto<Optional<Libro>> findById(@PathVariable Integer id) {//el @PathVariable indica que ese parametro esta en la url
			return libroS.findByIdLibro(id);
			
		}
		
//		Crear libro
		@PostMapping("/add")
		public MessageResponseDto<String> create(@RequestBody Libro book, @RequestHeader HttpHeaders headers) { // el @RequestBody indica que tiene que coger la info del cuerpo de la request
			System.out.println(headers.get("User-Agent")); //con HttpHeaders e imprimiendo esta línea sabemos desde donde se hace la peticion
			return libroS.createLibro(book);
		}
		
//		Actualizar libro existente
		@PutMapping("/editar/{id}")
		public MessageResponseDto<String> update(@RequestBody Libro book,@PathVariable Integer id) {
			return libroS.updateLibro(book, id);
		}
		
//		Borrar libro libro
		@DeleteMapping("/delete/{id}")
		public MessageResponseDto<String> delete(@PathVariable Integer id){
			return libroS.deleteLibro(id);
		}
		
//		Borrar multiples libros
		@PostMapping("/deleteM")
		public MessageResponseDto<String> delete(@RequestBody ArrayList<Libro> libros){
			return libroS.deleteMultipleLibros(libros);
		}
		
		
//		Buscar todos los libros de un autor
		@GetMapping("/autor/{dni}")
		public MessageResponseDto<ArrayList<Libro>> findAllLibrosAutor(@PathVariable String dni){
			return libroS.librosAutor(dni);
		}
		
//		Buscar todos los libros de una categoria
		@GetMapping("/categoria/{id}")
		public MessageResponseDto<ArrayList<Libro>> findAllLibrosCategoria(@PathVariable Integer id){
			return libroS.librosCategoria(id);
		}
	
		//Exportar a excel
		@PostMapping("/exportExcel")
		public ModelAndView exportE(@RequestBody List<Libro> lista2) {
			
			return libroS.exportarExcel(lista2);
		}
}
