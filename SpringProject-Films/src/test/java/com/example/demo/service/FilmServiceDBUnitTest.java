package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.data.Film;
import com.example.demo.data.repo.FilmRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FilmServiceDBUnitTest {
	@Autowired
	private FilmServiceDB service;
	
	@MockBean
	private FilmRepo repo;
	
	@Test
	void testCreate() {
		
		Film newFilm = new Film("Whiplash", "Damien Chazelle",107,15);
		
		Film savedFilm = new Film(1,"Whiplash", "Damien Chazelle",107,15);
		
		Mockito.when(this.repo.save(newFilm)).thenReturn(savedFilm);
		
		assertThat(this.service.createFilm(newFilm)).isEqualTo(savedFilm);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newFilm);
		
	}
	@Test
	void testGetAll() {
		
		List<Film> testFilms = List.of(new Film(1,"Whiplash", "Damien Chazelle",107,15));
		
		Mockito.when(this.repo.findAll()).thenReturn(testFilms);
		
		assertThat(this.service.getAllFilms()).isEqualTo(testFilms);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
		
	}
	
	@Test
	void testGetFilmByName() {
		List<Film> testFilms = List.of(new Film(1,"Whiplash", "Damien Chazelle",107,15));
		
		String search = "whiplash";
		
		Mockito.when(this.repo.findByFilmTitleIgnoreCase(search)).thenReturn(testFilms);
		
		assertThat(this.service.getByName(search)).isEqualTo(testFilms);
		Mockito.verify(this.repo, Mockito.times(1)).findByFilmTitleIgnoreCase(search);
		
		
	}
	
	@Test
	void testGetFilmByID() {
		int id = 1;
		System.out.println("Step1");
		Film testFilm = new Film(id,"Whiplash", "Damien Chazelle",107,15);
		System.out.println("Step2");
		
		
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testFilm));
		System.out.println("Step3");
		assertThat(this.service.getFilm(id)).isEqualTo(testFilm);
		System.out.println("Step4");
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		
		
	}
	
	@Test
	void testUpdate() {
		// GIVEN 
		int id = 1;
		
		Film testFilm = new Film(id,"Whiplash", "Damien Chazelle",107,15);
		Film testNewFilm = new Film(id, "Iron Man", "Jon Favreau",126,12);
		
		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(testFilm));
	    Mockito.when(this.repo.save(new Film(id, "Iron Man", "Jon Favreau",126,12))).thenReturn(testNewFilm);
	    
	    Film actual = this.service.putFilm(id, testNewFilm);
	    // THEN
	    assertThat(actual).isEqualTo(testNewFilm);
	    
	    Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	    Mockito.verify(this.repo, Mockito.times(1)).save(new Film(id, "Iron Man", "Jon Favreau",126,12));
	   
		
	}
	
	@Test
	void testDeleteSucceeds() {
		int id =1;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertThat(this.service.deleteFilm(id)).isEqualTo("Deleted: " + id);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	void testDeleteFails() {
		int id=1;
		
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		
		assertThat(this.service.deleteFilm(id)).isEqualTo("Not deleted: " + id);
		
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
	}
	
}
