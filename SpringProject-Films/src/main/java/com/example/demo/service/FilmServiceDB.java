package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.data.Film;
import com.example.demo.data.repo.FilmRepo;

@Service
@Primary
public class FilmServiceDB implements FilmService{
	
	private FilmRepo repo;
	
	public FilmServiceDB(FilmRepo repo) {
		super();
		this.repo = repo;
	}
	@Override
	public Film createFilm(Film film) {
		return this.repo.save(film);
	}
	
	@Override
	public List<Film> getAllFilms(){
		return this.repo.findAll();
	}
	
	@Override
	public String deleteFilm(int id) {
	
		this.repo.deleteById(id);
		return "Deleted: " +id;
	}

}
