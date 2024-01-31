package edu.ucsb.cs156.spring.backenddemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;

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


@WebMvcTest(value = PublicHolidaysController.class)
public class PublicHolidaysControllerTests {
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  PublicHolidayQueryService mockPublicHolidayQueryService;


  @Test
  public void test_getPublicHolidays() throws Exception {
  
    String fakeJsonResult = "{ \"fake\" : \"result\" }";
    String year = "2003";
    String countryCode = "US";
    when(mockPublicHolidayQueryService.getJSON(eq(year), eq(countryCode))).thenReturn(fakeJsonResult);

    String url = String.format("/api/publicholidays/get?year=%s&countryCode=%s", year, countryCode);

    MvcResult response = mockMvc
        .perform( get(url).contentType("application/json"))
        .andExpect(status().isOk()).andReturn();

    String responseString = response.getResponse().getContentAsString();

    assertEquals(fakeJsonResult, responseString);
  }

}