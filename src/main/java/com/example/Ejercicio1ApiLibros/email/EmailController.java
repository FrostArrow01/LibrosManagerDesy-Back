package com.example.Ejercicio1ApiLibros.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Ejercicio1ApiLibros.utils.MessageResponseDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/email")
@Tag(name="Controlador de Email")
public class EmailController {
	
	@Autowired
	private EmailService emailS;
	
	@PostMapping("/send")
	public  MessageResponseDto<String> sendEmail(@RequestBody Email email) {
		return emailS.sendEmail(email);
	}

	@PostMapping("/sendArgs")
	public  MessageResponseDto<String> sendEmailArgs(
			@RequestParam(required=true) String to,
			@RequestParam(required=true) String subject,
			@RequestParam(required=true) String text,
			@RequestPart(required = false) MultipartFile file) {
		
		return emailS.sendEmailArgs(to, subject, text, file);
	}
}
