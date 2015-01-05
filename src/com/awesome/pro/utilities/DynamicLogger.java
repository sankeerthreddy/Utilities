package com.awesome.pro.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;

/**
 * @author sankeerth.reddy
 *	FileAppender which creates new logfile for every test run
 */
public class DynamicLogger extends FileAppender {

	public DynamicLogger() {
	}

	public DynamicLogger(Layout layout, String filename,
			boolean append, boolean bufferedIO, int bufferSize)
					throws IOException {
		super(layout, filename, append, bufferedIO, bufferSize);
	}

	public DynamicLogger(Layout layout, String filename,
			boolean append) throws IOException {
		super(layout, filename, append);
	}

	public DynamicLogger(Layout layout, String filename)
			throws IOException {
		super(layout, filename);
	}

	public void activateOptions() {
		if (fileName != null) {
			try {
				fileName = getNewLogFileName();
				setFile(fileName, fileAppend, bufferedIO, bufferSize);
			} catch (Exception e) {
				errorHandler.error("Error while activating log options", e,
						ErrorCode.FILE_OPEN_FAILURE);
			}
		}
	}

	private String getNewLogFileName() {
		if (fileName != null) {
			final String DOT = ".";
			final String HIPHEN = "-";
			final File logFile = new File(fileName);
			final String fileName = logFile.getName();
			String newFileName = "";

			final int dotIndex = fileName.indexOf(DOT);
			if (dotIndex != -1) {
				newFileName = StringUtils.substringBeforeLast(fileName, DOT) + HIPHEN +
						SystemTimeUtility.getSystemDate() + DOT
						+ StringUtils.substringAfterLast(fileName, DOT);
			} else {
				newFileName = fileName + System.currentTimeMillis();
			}
			return logFile.getParent() + File.separator+newFileName;
		}
		return null;
	}
}