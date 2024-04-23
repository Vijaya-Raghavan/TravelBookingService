package com.cloudbees.traveller.service;

import com.cloudbees.traveller.exception.TrainNotFoundException;
import com.cloudbees.traveller.exception.TravelApplicationException;
import com.cloudbees.traveller.model.Station;
import com.cloudbees.traveller.model.Train;
import com.cloudbees.traveller.repository.TrainDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

	@Autowired
	private TrainDataAccess trainDataAccess;

	public List<Train> getAllTrains() throws TravelApplicationException {
		try {
			Iterable<com.cloudbees.traveller.repository.entity.Train> trainsData = trainDataAccess.findAll();
			List<Train> trains = Collections.emptyList();
			if (trainsData != null) {
				trains = new ArrayList<>();
				Iterator<com.cloudbees.traveller.repository.entity.Train> itr = trainsData.iterator();
				while (itr.hasNext()) {
					com.cloudbees.traveller.repository.entity.Train trainData = itr.next();
					Train train = getTrain(trainData);
					trains.add(train);
				}
			}
			return trains;
		} catch (Exception e) {
			throw new TravelApplicationException("Unable to fetch trains");
		}
	}

	public Train findTrain(final Integer trainNumber) throws TravelApplicationException {
		try {
			Optional<com.cloudbees.traveller.repository.entity.Train> trainData = trainDataAccess.findById(trainNumber);
			Train train = null;
			if (trainData.isPresent()) {
				com.cloudbees.traveller.repository.entity.Train data = trainData.get();
				train = getTrain(data);
			} else {
				throw new TrainNotFoundException(trainNumber);
			}
			return train;
		} catch (Exception e) {
			throw new TravelApplicationException("Unable to fetch train");
		}
	}

	private Train getTrain(final com.cloudbees.traveller.repository.entity.Train trainData) {
		Train train = new Train();
		train.setName(trainData.getName());
		train.setNumber(trainData.getNumber());
		train.setDepartureTime(trainData.getDepartureTime());
		train.setArrivalTime(trainData.getArrivalTime());
		Station sourceStation = new Station();
		sourceStation.setName(trainData.getSource().getName());
		sourceStation.setCode(trainData.getSource().getCode());
		Station destinationStation = new Station();
		destinationStation.setName(trainData.getDestination().getName());
		destinationStation.setCode(trainData.getDestination().getCode());
		train.setSource(sourceStation);
		train.setDestination(destinationStation);
		return train;
	}
}
