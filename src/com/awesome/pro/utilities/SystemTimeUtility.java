package com.awesome.pro.utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

/**
 * Utility to alter system time.
 * @author siddharth.s
 */
@SuppressWarnings("deprecation")
public class SystemTimeUtility {
	
	private static Logger LOGGER = Logger.getLogger(SystemTimeUtility.class);

	/**
	 * Windows command to change the system time.
	 */
	private final static String TIME_COMMAND = "cmd /C time ";

	/**
	 * Windows command to determine whether user is admin.
	 */
	private final static String ADMIN_COMMAND = "reg query \"HKU\\S-1-5-19\"";

	/**
	 * Positively offsets the current system time.
	 * @param hours Hours to add.
	 * @param minutes Minutes to add.
	 * @throws InterruptedException 
	 */
	public static void positiveOffset(int hours, int minutes) {
		if(!isAdmin()) {
			System.out.println("Elevated privileges are required to change system time. Run as administrator.");
			LOGGER.error("Elevated privileges are required to change system time. Run as administrator.");
			return;
		}
		
		long offset = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
		long currMillis = System.currentTimeMillis();
		
		Date date = new Date(currMillis);
		LOGGER.info("System time before change: " + date);
		
		date = new Date(currMillis + offset);
		LOGGER.info("System time after change: " + date);
		String dateString = date.getHours() + ":" + date.getMinutes();
		CommandPromptUtility.executeCommand(TIME_COMMAND + dateString);
	}

	/**
	 * Negatively offsets the current system time.
	 * @param hours Hours to subtract.
	 * @param minutes Minutes to subtract.
	 */
	public static void negativeOffset(int hours, int minutes) {
		if(!isAdmin()) {
			System.out.println("Elevated privileges are required to change system time. Run as administrator.");
			LOGGER.error("Elevated privileges are required to change system time. Run as administrator.");
			return;
		}
		
		long offset = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
		long currMillis = System.currentTimeMillis();
		
		Date date = new Date(currMillis);
		LOGGER.info("System time before change: " + date);

		date = new Date(currMillis - offset);
		LOGGER.info("System time after change: " + date);
		String dateString = date.getHours() + ":" + date.getMinutes();
		CommandPromptUtility.executeCommand(TIME_COMMAND + dateString);
	}
	
	/**
	 * @return Boolean status of whether the Java process is being
	 * run with administrator privileges or not.
	 */
	private static boolean isAdmin() {
		int exitValue = CommandPromptUtility.getExitValueOfCommand(ADMIN_COMMAND);
		if (exitValue == 0)
			return true;
		return false;
	}
	
	public static String getSystemDate(){
		 Date date1 = TimestampParser.convertTimestamp(String.valueOf(System.currentTimeMillis()));
		 DateTime jodaDate = new DateTime(date1);
		 String date = (jodaDate.getYear() + "_" + jodaDate.getMonthOfYear() + "_" + jodaDate.getDayOfMonth()
				 + "_" + jodaDate.getHourOfDay() + "_" + jodaDate.getMinuteOfHour() + "_" 
				 + jodaDate.getSecondOfMinute()).toString();
		 return date;
	}

}
