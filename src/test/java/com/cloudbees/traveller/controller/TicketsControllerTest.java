package com.cloudbees.traveller.controller;

import com.cloudbees.traveller.controller.util.ApplicationTestUtil;
import com.cloudbees.traveller.controller.util.LocalDateTimeTypeAdapter;
import com.cloudbees.traveller.controller.util.LocalDateTypeAdapter;
import com.cloudbees.traveller.controller.util.LocalTimeTypeAdapter;
import com.cloudbees.traveller.model.Passenger;
import com.cloudbees.traveller.model.Station;
import com.cloudbees.traveller.model.Train;
import com.cloudbees.traveller.model.Travel;
import com.cloudbees.traveller.model.request.TravelRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(TicketsController.class)
public class TicketsControllerTest {

	private static Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
			.registerTypeAdapter(LocalTime.class, new LocalTimeTypeAdapter())
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
			.create();


	@Autowired
	private MockMvc mvc;

	@Test
	public void bockTicket() throws Exception
	{
		TravelRequest travelRequest = new TravelRequest();
		Travel travel = new Travel();
		Train train = new Train();
		train.setNumber(60002);
		travel.setTrain(train);
		travel.setTravelDate(LocalDate.parse("2024-04-22"));
		Station from = new Station();
		from.setCode("LON");
		travel.setFrom(from);
		Station to = new Station();
		to.setCode("PAR");
		travel.setTo(to);
		travelRequest.setTravelInfo(travel);
		List<Passenger> passengers = new ArrayList<>();
		Passenger passenger = new Passenger();
		passenger.setFirstName("Ajay");
		passenger.setLastName("A");
		passenger.setAge(10);
		Passenger passenger2 = new Passenger();
		passenger2.setFirstName("Vijay");
		passenger2.setLastName("A");
		passenger2.setAge(20);
		passengers.add(passenger);
		passengers.add(passenger2);
		travelRequest.setPassengersInfo(passengers);


		mvc.perform( MockMvcRequestBuilders
						.post("/tickets")
						.header(HttpHeaders.AUTHORIZATION, ApplicationTestUtil.getAuth())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.with(SecurityMockMvcRequestPostProcessors.csrf().asHeader())
						.content(asJsonString(travelRequest)))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.travelBookingInfo.bookingId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.passengersBookingInfo[*].passengerId").isNotEmpty());
	}

	@Test
	public void getAllTickets() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders
						.get("/tickets")
						.header(HttpHeaders.AUTHORIZATION, ApplicationTestUtil.getAuth())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.with(SecurityMockMvcRequestPostProcessors.csrf().asHeader()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].travelBookingInfo.bookingId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].passengersBookingInfo[*].passengerId").isNotEmpty());
	}

	public static String asJsonString(final Object obj) {
		try {
			return gson.toJson(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
