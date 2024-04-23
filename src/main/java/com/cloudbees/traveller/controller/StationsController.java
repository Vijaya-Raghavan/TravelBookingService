package com.cloudbees.traveller.controller;

import com.cloudbees.traveller.exception.StationNotFoundException;
import com.cloudbees.traveller.model.Station;
import com.cloudbees.traveller.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationsController {

	@Autowired
	private StationService stationService;

	@GetMapping
	public ResponseEntity<Object> getAll() {
		ResponseEntity<Object> response;
		try {
			List<Station> stations = stationService.getAllStations();
			response = ResponseEntity.ok(stations);
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@GetMapping("/{station_code}")
	public ResponseEntity<Object> getByCode(@PathVariable("station_code") final String code) {
		ResponseEntity<Object> response;
		try {
			Station station = stationService.findStation(code);
			response = ResponseEntity.ok(station);
		} catch (StationNotFoundException e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

}
