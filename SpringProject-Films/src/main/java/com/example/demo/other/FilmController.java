package com.example.demo.other;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Film;
import com.example.demo.service.FilmService;

@RestController
@CrossOrigin
public class FilmController {

	private FilmService service;
	
	public FilmController(FilmService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String hello() {

		return "Hello World";
	}
	
	@PostMapping("/createFilm")
	public ResponseEntity<Film> createFilm(@RequestBody Film film){
		
	}
	
}
