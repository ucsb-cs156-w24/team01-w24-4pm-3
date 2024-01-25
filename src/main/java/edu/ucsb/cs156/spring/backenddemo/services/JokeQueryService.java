package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// taken from example EarthquakeQueryService
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
// taken from example EarthquakeQueryService
import java.util.List;
import java.util.Map;
// taken from example EarthquakeQueryService
@Slf4j


@Service
public class JokeQueryService {

    private final RestTemplate restTemplate;

    public JokeQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://v2.jokeapi.dev/joke/{category}?amount={numJokes}";

    public String getJSON(String category, int numJokes) throws HttpClientErrorException {
        log.info("category={}, numJokes={}", category, numJokes);

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, String> uriVariables = Map.of(
            "category", category,
            "numJokes", String.valueOf(numJokes)
        );

        ResponseEntity<String> re = restTemplate.exchange(
            ENDPOINT, 
            HttpMethod.GET, 
            entity, 
            String.class, 
            uriVariables
        );

        return re.getBody();
        // return "";
    }
}