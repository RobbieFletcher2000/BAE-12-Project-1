package com.example.demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment
	private int id;
	
	private String filmTitle;
	private String director;
	private int runtime;
	private int ageRating;
	
	public Film(int id, String title, String director, int runtime, int ageRating) {
		super();
		this.id = id;
		this.filmTitle = title;
		this.director = director;
		this.runtime = runtime;
		this.ageRating = ageRating;	
	}
	public Film(String title, String director, int runtime, int ageRating) {
		super();
		this.filmTitle = title;
		this.director = director;
		this.runtime = runtime;
		this.ageRating = ageRating;	
	}
	public Film() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return filmTitle;
	}

	public void setName(String title) {
		this.filmTitle = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setAge(int runtime) {
		this.runtime = runtime;
	}

	public int getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(int ageRating) {
		this.ageRating = ageRating;
	}

}
