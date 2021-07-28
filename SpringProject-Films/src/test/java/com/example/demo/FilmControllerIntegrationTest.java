package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
