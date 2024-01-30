package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag(name = "Home Page with links to documentation")
@Slf4j
@RestController
public class HomeController {

    @Operation(summary = "Get general info about the server, including link to api documentation")
    @GetMapping("/")
    public ResponseEntity<String> getHome() throws JsonProcessingException {
        log.info("Home Page accessed");
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        // builder.scheme("http");
        URI uri = builder.build().toUri();

        String body = getHomePageObjectJSON(uri.toString());
        return ResponseEntity.ok().body(body);
    }

    public static String getHomePageObjectJSON(String baseUrl) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("greeting", "Greetings from Spring Boot!");

        List<String> team = new ArrayList<String>();
        team.add("Laya P."); 
	team.add("Advait A."); 
	team.add("Konark V."); 
	team.add("Sid R."); 
	team.add("Hugo L."); 
	team.add("Bonnie H.");
        resultMap.put("team", team);
        resultMap.put("repo", "https://github.com/ucsb-cs156-w24/team01-w24-4pm-3");
        resultMap.put("api-documentation", baseUrl + "swagger-ui/index.html");
        return mapper.writeValueAsString(resultMap);
    }
}
