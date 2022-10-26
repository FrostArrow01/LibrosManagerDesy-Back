package com.example.Ejercicio1ApiLibros.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Autor")
public class Autor {
	@Id
	private String dni;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido1;
	private String apellido2;	
	private String telefono;
	private String email;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Libro> libro_coleccion;
	
}
