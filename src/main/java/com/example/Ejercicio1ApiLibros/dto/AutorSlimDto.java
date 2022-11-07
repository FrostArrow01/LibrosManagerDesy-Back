package com.example.Ejercicio1ApiLibros.dto;

import java.util.List;
import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class AutorSlimDto {
	private String nombre;
	private String apellido1;
	private String apellido2;
	

	@JsonIgnore
	private List<Libro> libros_coleccion;


	
	
}
