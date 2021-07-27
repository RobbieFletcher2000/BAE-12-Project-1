package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.data.Film;

@Service
public class FilmServiceList implements FilmService {
	
	public String hello() {
		return "Hello World";
	}
	
	private ArrayList<Film> films = new ArrayList<>();
	
	@Override
	public Film createFilm(Film film) {
		System.out.println(film);
		this.films.add(film);
		return this.films.get(this.films.size() -1);
	}
	
	@Override
	public ArrayList<Film> getAllFilms(){
		
		for(Film film: films) {
			System.out.println(film);
		}
		return films;
	}
	
	@Override
	public String deleteFilm(int id) {
		this.films.remove(id);
		return "Deleted film at index: " + id;
	}

}
