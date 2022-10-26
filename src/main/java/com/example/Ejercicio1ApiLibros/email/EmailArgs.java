package com.example.Ejercicio1ApiLibros.email;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailArgs extends Email{
	private File file;
}
