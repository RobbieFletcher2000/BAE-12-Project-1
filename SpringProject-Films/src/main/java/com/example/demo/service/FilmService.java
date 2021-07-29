package com.example.demo.service;

import java.util.List;

import com.example.demo.data.Film;

public interface FilmService {
	
	public Film createFilm(Film film);
	
	public List<Film> getAllFilms();
	
	public String deleteFilm(int id);
	
	public Film getFilm(int id);
	
	public List<Film> getByName(String filmTitle);
	
	public Film putFilm(int id, Film newFilm);

}
