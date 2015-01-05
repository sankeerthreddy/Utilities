package com.awesome.pro.utilities;

import java.util.Date;

/**
 * This class is used to parse the time stamp in milliseconds into
 * a date.
 * @author Siddharth.S
 */
public class TimestampParser {
	
	/**
	 * @return Parsed java.util.date
	 * @param timestamp String value of time stamp
	 */
	public static Date convertTimestamp(String timestamp) {
		long millseconds = Long.parseLong(timestamp);
		return new Date(millseconds);
	}

}
