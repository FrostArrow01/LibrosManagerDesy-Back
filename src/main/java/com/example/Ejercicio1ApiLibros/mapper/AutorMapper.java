package com.example.Ejercicio1ApiLibros.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Ejercicio1ApiLibros.dto.AutorSlimDto;
import com.example.Ejercicio1ApiLibros.entities.Autor;
import com.example.Ejercicio1ApiLibros.repositories.AutorRepository;

@Component
public class AutorMapper {
	
	@Autowired
	private AutorRepository autorRepository;
	

	
	public AutorSlimDto convertEntityToDto(Autor autor) { //de entity a dto
		ModelMapper modelMapper = new ModelMapper();
		AutorSlimDto autorSlimDto = modelMapper.map(autor, AutorSlimDto.class);
		return autorSlimDto;
	}
	
	public Autor convertDtoToEntity(AutorSlimDto autorSlimDto) { //de dto a entity
		ModelMapper modelMapper = new ModelMapper();
		Autor autor = modelMapper.map(autorSlimDto, Autor.class);
		return autor;
	}
}
