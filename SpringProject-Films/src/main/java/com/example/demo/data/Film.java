package com.example.demo.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment
	private int id;
	
	@Column(name = "Title", unique= true)
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + runtime;
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		result = prime * result + ageRating;
		result = prime * result + id;
		result = prime * result + ((filmTitle == null) ? 0 : filmTitle.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (runtime != other.runtime)
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (ageRating != other.ageRating)
			return false;
		if (id != other.id)
			return false;
		if (filmTitle == null) {
			if (other.filmTitle != null)
				return false;
		} else if (!filmTitle.equals(other.filmTitle))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Film [Title =" + filmTitle + ", Director =" + director + ", Runtime =" + runtime + ", Age Rating =" + ageRating + "]";
	}

}
