package com.cloudbees.traveller.controller.util;

import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;

public class ApplicationTestUtil {

	public static String getAuth() {
		return "Basic " + HttpHeaders.encodeBasicAuth("vijayaraghavan1805@gmail.com", "Vrag123#", Charset.defaultCharset());
	}
}
