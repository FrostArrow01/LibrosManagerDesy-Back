package com.example.Ejercicio1ApiLibros.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ejercicio1ApiLibros.entities.Autor;
import com.example.Ejercicio1ApiLibros.entities.Categoria;
import com.example.Ejercicio1ApiLibros.repositories.AutorRepository;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AutorService {
	@Autowired
	private AutorRepository autorR;
	
	//buscar autores
	public MessageResponseDto<List<Autor>> findAllAutor(){
		try {
			autorR.findAll();
			return MessageResponseDto.success(autorR.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al recoger todos los autores");
		}
	}
	
	//buscar autor por id
	public MessageResponseDto<Autor> findByIdAutor(String dni) {
		try {
			 Optional<Autor> autor = autorR.findById(dni);
			 if(autor.isPresent()) { //comprobamos que existe y que no es null
				 return MessageResponseDto.success(autor.get()); //mandamos el succes con el autor tipo Autor (.get al observable)
			 } else {
				return MessageResponseDto.fail("Ha habido un error al encontrar el autor por id");
			 }
		} catch (Exception e) {
			return MessageResponseDto.fail("Ha habido un error al encontrar el autor por id");
		}
	}
	
	//crear autor
	public MessageResponseDto<String> createAutor(Autor autor) {
		try {
			autorR.save(autor);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al guardar el autor");
		}
	}
	
	//editar autor
	public MessageResponseDto<String> updateAutor(Autor autor, String dni) {
			try {
				
				autor.setDni(dni);
				autorR.save(autor);	
				return MessageResponseDto.success("Ok");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return MessageResponseDto.fail("Error al editar el autor " +e.getMessage());
			}
	}
	
	//borrar autor
	public MessageResponseDto<String> deleteAutor(String dni) {
		try {
			autorR.deleteById(dni);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Error al borrar el autor: " +e.getMessage());
		}
	}
	
	//borrar varios autores
		public MessageResponseDto<String> deleteMultipleLibros(ArrayList<Autor> autores){
			try {
				for (Autor autor : autores) {
					autorR.deleteById(autor.getDni());
				}
				return MessageResponseDto.success("Ok");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return MessageResponseDto.fail("Error al eliminar los autores");
			}
			
		}
}
