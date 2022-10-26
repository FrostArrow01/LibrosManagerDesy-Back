package com.example.Ejercicio1ApiLibros.email;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Email {
	private String to;
	private String subject;
	private String text;

	
	
}
