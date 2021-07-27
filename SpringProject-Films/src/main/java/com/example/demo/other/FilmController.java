package com.example.demo.other;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		Film created = this.service.createFilm(film);
		return new ResponseEntity<>(created,HttpStatus.OK);
	}
	
	@GetMapping("/getAllFilms")
	public ResponseEntity<List<Film>> getAllFilms(){
		List<Film> created = this.service.getAllFilms();
		return new ResponseEntity<>(created,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteFilm/{id}")
	public ResponseEntity<String> deleteFilm(@PathVariable int id){
		String body = this.service.deleteFilm(id);
		return new ResponseEntity<String>(body,HttpStatus.NO_CONTENT);
		
	}
	
}
