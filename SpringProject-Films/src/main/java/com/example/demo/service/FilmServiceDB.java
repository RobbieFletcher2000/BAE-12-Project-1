package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		if(this.repo.existsById(id)) {
			return "Not deleted: " + id;
		}else {
			return "Deleted: " +id;
		}
		
	}
	
	@Override
	@Transactional
	public Film getFilm(int id) {
		Film found = this.repo.findById(id).get();
		return found;
	}
	
	@Override
	public List<Film> getByName(String filmTitle) {
		return this.repo.findByFilmTitleIgnoreCase(filmTitle);
	}
	@Override
	public Film putFilm(int id, Film newFilm) {
		// pull out existing record
				Film found = this.repo.findById(id).get();

				System.out.println("FOUND: " + found);
				
				// modify record
				found.setRuntime(newFilm.getRuntime());
				found.setTitle(newFilm.getTitle());
				found.setDirector(newFilm.getDirector());
				found.setAgeRating(newFilm.getAgeRating());
				
				System.out.println("FOUND AFTER UPDATE: " + found);
				// save it back to overwrite it
				Film updated = this.repo.save(found);
				System.out.println("UPDATED: " + updated);

				return updated;
	}

}
