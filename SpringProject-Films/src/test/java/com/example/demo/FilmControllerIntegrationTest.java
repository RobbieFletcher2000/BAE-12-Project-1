package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.data.Film;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // try random ports until it finds a free one
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:film-schema.sql",
"classpath:film-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // sets profile for the test class
public class FilmControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception{
		Film testFilm = new Film("Whi", "Damien Chazelle",107,15);
		// convert to json
		String testFilmAsJSON = this.mapper.writeValueAsString(testFilm);
		
		System.out.println(testFilm);
		System.out.println(testFilmAsJSON);
		
		// body, method, address and the content-type header
		RequestBuilder request = post("/createFilm").contentType(MediaType.APPLICATION_JSON).content(testFilmAsJSON);
		
		
		// check the response body and status

		ResultMatcher checkStatus = status().is(201);
		
		
		Film testCreatedFilm = new Film("Whi", "Damien Chazelle",107,15);
		testCreatedFilm.setId(3);
		String testCreatedFilmAsJSON = this.mapper.writeValueAsString(testCreatedFilm);
		
		ResultMatcher checkBody = content().json(testCreatedFilmAsJSON);
		// SEND request and check status + body
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void testDelete() throws Exception{
		// create request
		
		RequestBuilder request = delete("/deleteFilm/1");
		
		
		//check response
		
		ResultMatcher checkStatus = status().is(204);
		ResultMatcher checkBody = content().string("Deleted: 1");
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception{
		
		List<Film> allFilms = new ArrayList<>();
		
		Film testFilm1 = new Film(1,"Whiplash","Damien Chazelle",107,15);
		Film testFilm2 = new Film(2,"The Gentlemen", "Guy Ritchie",113,18);
		
		allFilms.add(testFilm1);
		allFilms.add(testFilm2);
	
		
		String listAsJSON = this.mapper.writeValueAsString(allFilms);
		//create request
	    RequestBuilder request = get("/getAllFilms").contentType(MediaType.APPLICATION_JSON).content(listAsJSON);
	
	
		
	// check response
		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().string(listAsJSON);		
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
	}
	@Test
	void testGetFilmByID() throws Exception{
		
		Film testFilm = new Film(1,"Whiplash","Damien Chazelle",107,15);
		
		String checkedFilmAsJSON = this.mapper.writeValueAsString(testFilm);
		
		
		RequestBuilder request = get("/getFilm/1").contentType(MediaType.APPLICATION_JSON).content(checkedFilmAsJSON);
		
		
		ResultMatcher checkStatus = status().is(200);
		ResultMatcher checkBody = content().string(checkedFilmAsJSON);
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
		
		
	}
		@Test
		void testGetByName() throws Exception{
			List<Film> allFilms = new ArrayList<>();
			
			Film testFilm2 = new Film(2,"The Gentlemen", "Guy Ritchie",113,18);
			
			allFilms.add(testFilm2);
			
			
			String listAsJSON = this.mapper.writeValueAsString(allFilms);
			
			
			RequestBuilder request = get("/getByName/The Gentlemen").contentType(MediaType.APPLICATION_JSON).content(listAsJSON);
			
			// check response
			ResultMatcher checkStatus = status().is(200);
			ResultMatcher checkBody = content().json(listAsJSON);	
			System.out.println(listAsJSON);			
		    this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);			
			
		}
		
		@Test
		void testUpdate() throws Exception {
			int id = 1;
			Film newFilm = new Film(id, "Iron Man", "Jon Favreau",126,12);
			String newFilmAsJSON = this.mapper.writeValueAsString(newFilm);

			RequestBuilder req = put("/putFilm/" + id).contentType(MediaType.APPLICATION_JSON)
					.content(newFilmAsJSON);

			ResultMatcher checkStatus = status().is(200);

			ResultMatcher checkBody = content().json(newFilmAsJSON);

			this.mockMVC.perform(req).andExpect(checkStatus).andExpect(checkBody);
		}
}
