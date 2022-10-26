package com.example.Ejercicio1ApiLibros.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Ejercicio1ApiLibros.entities.Autor;
import com.example.Ejercicio1ApiLibros.entities.Categoria;
import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.example.Ejercicio1ApiLibros.repositories.CategoriaRepository;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaService {
	@Autowired
	private final CategoriaRepository categoriaR;
	
	//buscar todas las categorias
	public MessageResponseDto<List<Categoria>> findAllCategoria(){
		try {
			return MessageResponseDto.success(categoriaR.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al recoger todas las categorias");
		}
	}
	
	//buscar categoria por id
	public MessageResponseDto<Categoria> findCategoriabyId(Integer id) {
		try {
			 Optional<Categoria> categoria = categoriaR.findById(id);
			 if(categoria.isPresent()) { //comprobamos que existe y que no es null
				 return MessageResponseDto.success(categoria.get()); //mandamos el succes con el autor tipo Autor (.get al observable)
			 } else {
				return MessageResponseDto.fail("Ha habido un error al encontrar la categoria por id");
			 }
		} catch (Exception e) {
			return MessageResponseDto.fail("Ha habido un error al encontrar la categoria por id");
		}
	}
	
	//crear categoria
	public MessageResponseDto<String> addCategoria(Categoria categoria) {
		try {
			categoriaR.save(categoria);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al crear una categoria");
		}
	}
	
	//actualizar categoria
	public MessageResponseDto<String> updateCategoria(Integer id, Categoria cuerpo) {
		try {
			cuerpo.setId(id);
			categoriaR.save(cuerpo);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al actualizar la categoria");
		}
		
	}
	
	
	//borrar categoria por id
	public MessageResponseDto<String> deleteCategoriabyId(Integer id) {
		try {
			categoriaR.deleteById(id);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Error al borrar la categoria: " +e.getMessage());
		}
	}
	
	//borrar varias categorias
	public MessageResponseDto<String> deleteMultipleLibros(ArrayList<Categoria> categorias){
		try {
			for (Categoria cate : categorias) {
				categoriaR.deleteById(cate.getId());
			}
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Error al eliminar las categorias");
		}
		
	}
	
	
}
