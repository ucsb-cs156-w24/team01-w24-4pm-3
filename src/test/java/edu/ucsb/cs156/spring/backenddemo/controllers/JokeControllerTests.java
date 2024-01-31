package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;


@WebMvcTest(value = JokeController.class)
public class JokeControllerTests {
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  JokeQueryService mockJokeQueryService;


  @Test
  public void test_getJokes() throws Exception {
  
    String fakeJsonResult = "{ \"fake\" : \"result\" }";
    String category = "Programming";
    int numJokes = 1;
    when(mockJokeQueryService.getJSON(eq(category), eq(numJokes))).thenReturn(fakeJsonResult);

    String url = String.format("/api/jokes/get?category=%s&amount=%d", category, numJokes);

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}