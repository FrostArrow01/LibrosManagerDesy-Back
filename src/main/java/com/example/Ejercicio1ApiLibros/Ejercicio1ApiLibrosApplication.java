package com.example.Ejercicio1ApiLibros;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.Ejercicio1ApiLibros.email.EmailService;
import com.example.Ejercicio1ApiLibros.repositories.AutorRepository;
import com.example.Ejercicio1ApiLibros.repositories.CategoriaRepository;
import com.example.Ejercicio1ApiLibros.repositories.LibroRepository;



@SpringBootApplication
public class Ejercicio1ApiLibrosApplication {
	
	
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejercicio1ApiLibrosApplication.class, args);
		LibroRepository libroR = context.getBean(LibroRepository.class);
		CategoriaRepository categoriaR = context.getBean(CategoriaRepository.class);
		AutorRepository autorR = context.getBean(AutorRepository.class);

		
		
////		creamos libros, autores y categorias
//		Autor autor1 = new Autor("79797979V","J.","K.","Rowling","68686868", "ejemplo@ejemplo.com");
//		Categoria categoria1 = new Categoria(null, "Fantasia");
//		Libro libro1 = new Libro(null, "Harry Potter",1,79797979V,1);
//		
//		libroR.save(libro1);
//		autorR.save(autor1);
//		categoriaR.save(categoria1);
		
		
	}
	@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
