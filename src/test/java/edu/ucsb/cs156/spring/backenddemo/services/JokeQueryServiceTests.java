package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(JokeQueryService.class)
public class JokeQueryServiceTests {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private JokeQueryService jokeQueryService; 

    @Test
    public void test_getJSON() {

        String category = "Any";
        int numJokes = 5;
        String expectedURL = JokeQueryService.ENDPOINT.replace("{category}", category)
                .replace("{numJokes}", String.valueOf(numJokes));

        String fakeJsonResult = "{ \"joke\" : \"This is a fake joke.\" }";

        this.mockRestServiceServer.expect(requestTo(expectedURL))
                .andExpect(header("Accept", MediaType.APPLICATION_JSON.toString()))
                .andExpect(header("Content-Type", MediaType.APPLICATION_JSON.toString()))
                .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

        String actualResult = jokeQueryService.getJSON(category, numJokes);
        assertEquals(fakeJsonResult, actualResult);
    }
}
