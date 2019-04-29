package br.com.salescenter.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public static String formatDate(LocalDate date) {		
		return date.format(dateFormatter);
	}
	
	public static String now() {
		return LocalDateTime.now().format(dateTimeFormatter);
	}

	public static LocalDate today() {
		return LocalDate.parse(LocalDate.now().format(dateFormatter), dateFormatter);
	}

	public static int currentYear() {
		return LocalDate.now().getYear();
	}

	public static LocalDate minimumHireDate() {
		return LocalDate.parse(LocalDate.now().minus(Period.ofYears(16)).format(dateFormatter), dateFormatter);
	}

	public static LocalDate maximumHireDate() {
		return LocalDate.parse(LocalDate.now().minus(Period.ofYears(70)).format(dateFormatter), dateFormatter);
	}

	public static LocalDate maximumSearchDate() {
		return LocalDate.of(2010, 1, 1);
	}

	public static String rangeInYearMinimumMaximum() {
		return maximumHireDate().getYear() + ":" + minimumHireDate().getYear();
	}

	public static String rangeInYearSearch() {
		return maximumSearchDate().getYear() + ":" + currentYear();
	}

	public static boolean fromAfterTo(LocalDate from, LocalDate to) {
		if (from.isAfter(to)) {
			return true;
		} else {
			return false;
		}
	}
}