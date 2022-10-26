package com.example.Ejercicio1ApiLibros.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Libro")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	private Integer id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private Integer edicion;
	@Column(nullable = false)
	private String fecha_lanzamiento;
	
	@ManyToOne //(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAutor")
	private Autor autor;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable=false)
	private Categoria categoria;
	

	

}
