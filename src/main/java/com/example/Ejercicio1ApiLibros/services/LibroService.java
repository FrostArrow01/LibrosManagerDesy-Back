package com.example.Ejercicio1ApiLibros.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.Ejercicio1ApiLibros.entities.Libro;
import com.example.Ejercicio1ApiLibros.repositories.LibroRepository;
import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;
import com.example.Ejercicio1ApiLibros.view.LibrosExcelView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LibroService {

	@Autowired
	private LibroRepository libroR;
	
	//buscar todos los libros
	public MessageResponseDto<List<Libro>> findAllLibro(){
		try {
			return MessageResponseDto.success(libroR.findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al recoger todos los libros");
		}
	}
	
	//buscar libro por ip
	public MessageResponseDto<Optional<Libro>> findByIdLibro(Integer id) {
		try {
			return MessageResponseDto.success(libroR.findById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al encontrar el libro por id");
		}
	}
	
	//crear libro
	public MessageResponseDto<String> createLibro(Libro book) {
		try {
			libroR.save(book);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al crear un libro");
		}
	}
	
	//actualizar libro
	public MessageResponseDto<String> updateLibro(Libro book, Integer id) {
		try {
			book.setId(id);
			libroR.save(book);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al actualizar libros");
		}
	}
	
	//borrar libro 
	public MessageResponseDto<String> deleteLibro(Integer id){
		try {
			libroR.deleteById(id);
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Error al borrar el libro");
		}
	}
	
	//borrar varios libros
	public MessageResponseDto<String> deleteMultipleLibros(ArrayList<Libro> libros){
		try {
			for (Libro book : libros) {
				libroR.deleteById(book.getId());
			}
			return MessageResponseDto.success("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Error al eliminar los libros");
		}
		
	}
	
//	//Libros de un autor
	public MessageResponseDto<ArrayList<Libro>> librosAutor(String dni){
		try {
			ArrayList<Libro> TodosLibros = (ArrayList<Libro>) findAllLibro().getMessage();
			ArrayList<Libro> LibrosAutorId = new ArrayList<Libro>();
			for(int i=0; i<TodosLibros.size();i++) {
				if (TodosLibros.get(i).getAutor().getDni().equals(dni)) {
					LibrosAutorId.add(TodosLibros.get(i));
				}
			}
			
			if(!LibrosAutorId.isEmpty()) {
				return MessageResponseDto.success(LibrosAutorId);
			}else {
				return MessageResponseDto.fail("Ha habido un error al encontrar los libros de un autor");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al encontrar los libros de un autor");
		}
	}
	
	
	//Libros de una categoria
	public MessageResponseDto<ArrayList<Libro>> librosCategoria(Integer id){
		try {
			ArrayList<Libro> TodosLibros = (ArrayList<Libro>) findAllLibro().getMessage();
			ArrayList<Libro> LibrosAutorId = new ArrayList<Libro>();
			for(int i=0; i<TodosLibros.size();i++) {
				if (TodosLibros.get(i).getCategoria().getId() == id) {
					LibrosAutorId.add(TodosLibros.get(i));
				}
			}
			return MessageResponseDto.success(LibrosAutorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return MessageResponseDto.fail("Ha habido un error al encontrar los libros de una categoria");
		}
	}

	//Exportar libros a excel
	public ModelAndView exportarExcel(List<Libro> lista2) {
		// TODO Auto-generated method stub
		Map<String, Object> model = new TreeMap<String, Object>();
		int i = 1;
		List<Libro> listLibros = lista2;
		for (Libro libro : listLibros) {
			model.put(String.valueOf(i), new String[] {
				libro.getTitulo(),
				String.valueOf(libro.getEdicion()),
				libro.getAutor().getNombre(),
				libro.getCategoria().getDescripcion()
			});
			i++;
		}
		return new ModelAndView(new LibrosExcelView(), model);
	}
	
}
