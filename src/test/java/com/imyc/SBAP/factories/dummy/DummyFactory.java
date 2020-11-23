package com.imyc.SBAP.factories.dummy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface DummyFactory<T> {
	
	T make();

	default Date currentDate() {
		String source = "1987-12-31";
		String pattern = "yyyy-mm-dd";
		try {
			Date date = new SimpleDateFormat(pattern).parse(source);
			return date;
		} catch (ParseException e) {
			System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", source, pattern);
		}
		return new Date();
	}
}
