package com.example.Ejercicio1ApiLibros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ejercicio1ApiLibros.entities.Libro;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
