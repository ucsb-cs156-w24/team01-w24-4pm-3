package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Tide Information from NOAA https://api.tidesandcurrents.noaa.gov/api/prod/")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary="Get water level for date range, in local time", description ="For station id, see: https://tidesandcurrents.noaa.gov/tide_predictions.html?gid=1393")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @Parameter(name="beginDate", description="beginDate in format yyyymmdd") @RequestParam String beginDate,
        @Parameter(name="endDate", description="endDate in format yyyymmdd") @RequestParam String endDate,
        @Parameter(name="station", description="station") @RequestParam String station
    ) throws JsonProcessingException {
        log.info("getTides: beginDate={}, endDate={}, station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }

}
