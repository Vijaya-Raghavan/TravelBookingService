package com.cloudbees.traveller.controller;

import com.cloudbees.traveller.exception.TrainNotFoundException;
import com.cloudbees.traveller.model.Train;
import com.cloudbees.traveller.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainsController {

	@Autowired
	private TrainService trainService;

	@GetMapping
	public ResponseEntity<Object> getAll() {
		ResponseEntity<Object> response;
		try {
			List<Train> trains = trainService.getAllTrains();
			response = ResponseEntity.ok(trains);
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@GetMapping("/{train_number}")
	public ResponseEntity<Object> getByTrainNumber(@PathVariable("train_number") final Integer trainNumber) {
		ResponseEntity<Object> response;
		try {
			Train train = trainService.findTrain(trainNumber);
			response = ResponseEntity.ok(train);
		} catch (TrainNotFoundException e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}
}
