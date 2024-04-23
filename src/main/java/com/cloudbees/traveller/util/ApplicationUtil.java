package com.cloudbees.traveller.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ApplicationUtil {

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static LocalDate parseDate(String date) {
		return LocalDate.parse(date, DATE_FORMAT);
	}

	public static LocalTime parseTime(String time) {
		return LocalTime.parse(time, TIME_FORMAT);
	}

	public static LocalDateTime parseDateTime(String timestamp) {
		return LocalDateTime.parse(timestamp, DATE_TIME_FORMAT);
	}

}
