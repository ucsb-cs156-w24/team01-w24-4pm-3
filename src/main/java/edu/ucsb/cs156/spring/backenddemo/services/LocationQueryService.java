package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationQueryService {

    private final RestTemplate restTemplate;

    public LocationQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://nominatim.openstreetmap.org/search/{location}?format=json";

    public String getJSON(String location) throws HttpClientErrorException {
        log.info("location={}", location);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> uriVariables = Map.of("location", location);

        HttpEntity<String> entity = new HttpEntity<>("body",headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,uriVariables);

        return re.getBody();
    }
}