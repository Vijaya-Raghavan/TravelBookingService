package com.cloudbees.traveller.service;

import com.cloudbees.traveller.exception.StationNotFoundException;
import com.cloudbees.traveller.exception.TravelApplicationException;
import com.cloudbees.traveller.model.Station;
import com.cloudbees.traveller.repository.StationDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StationService {

	@Autowired
	private StationDataAccess stationDataAccess;

	public List<Station> getAllStations() throws TravelApplicationException {
		try {
			Iterable<com.cloudbees.traveller.repository.entity.Station> stationsData = stationDataAccess.findAll();
			List<Station> stations = Collections.emptyList();
			if (stationsData != null) {
				stations = new ArrayList<>();
				Iterator<com.cloudbees.traveller.repository.entity.Station> itr = stationsData.iterator();
				while (itr.hasNext()) {
					com.cloudbees.traveller.repository.entity.Station stationData = itr.next();
					Station station = getStation(stationData);
					stations.add(station);
				}
			}
			return stations;
		} catch (Exception e) {
			throw new TravelApplicationException("Unable to fetch stations");
		}
	}

	public Station findStation(final String code) throws TravelApplicationException {
		try {
			Optional<com.cloudbees.traveller.repository.entity.Station> stationData = stationDataAccess.findByStationCode(code);
			Station station = null;
			if (stationData.isPresent()) {
				com.cloudbees.traveller.repository.entity.Station data = stationData.get();
				station = getStation(data);
			} else {
				throw new StationNotFoundException(code);
			}
			return station;
		} catch (Exception e) {
			throw new TravelApplicationException("Unable to fetch station");
		}
	}

	private Station getStation(final com.cloudbees.traveller.repository.entity.Station data) {
		Station station;
		station = new Station();
		station.setName(data.getName());
		station.setCode(data.getCode());
		return station;
	}

}
