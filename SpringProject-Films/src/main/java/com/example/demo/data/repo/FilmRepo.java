package com.example.demo.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.data.Film;

@Repository
public interface FilmRepo extends JpaRepository<Film,Integer>{
	
	List<Film> findByNameIgnoreCase(String name);

}
