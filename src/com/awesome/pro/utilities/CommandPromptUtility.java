package com.awesome.pro.utilities;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Utility to execute commands on command prompt.
 * @author siddharth.s
 */
public class CommandPromptUtility {
	
	/**
	 * Root logger instance.
	 */
	private static Logger LOGGER = Logger.getLogger(CommandPromptUtility.class);

	/**
	 * @param command Command to be executed.
	 */
	public static void executeCommand(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			LOGGER.error("Error executing command: " + command, e);
		}
	}

	/**
	 * @param command Command to be executed.
	 * @return Return code of the command.
	 */
	public static int getExitValueOfCommand(String command) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			LOGGER.error("Error executing command: " + command, e);
			return -1;
		}
       return process.exitValue();
	}
}
