package com.upflux.desafio.fullstack.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static Date getCurrentTimeAsDate() {
		return new Date(System.currentTimeMillis());
	}

	public static String getCurrentTimeAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	public static void logError(Exception e) {
		System.out.println(String.format("%s ERROR: %s", Utils.getCurrentTimeAsString(), e));
	}
	
}
