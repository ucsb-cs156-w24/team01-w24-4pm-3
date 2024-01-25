package edu.ucsb.cs156.spring.backenddemo.services;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class UniversityQueryService {


    private final RestTemplate restTemplate;

    public UniversityQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "http://universities.hipolabs.com/search?name={name}";

    public String getJSON(String name) throws HttpClientErrorException {
       return "";
    }
}
