package com.awesome.pro.utilities;

import org.apache.log4j.Logger;


/**
 * Assertions class is a wrapper on TestNG Assert.
 * @author siddharth.s
 */
public class Assert {

	/**
	 * Root logger instance.
	 */
	private static Logger LOGGER = Logger.getLogger(Assert.class);

	public static void assertEquals(int actual, int expected, String testcase) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}

	public static void assertEquals(int actual, int expected) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}

	public static void assertEquals(String actual, String expected, String testcase) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}

	public static void assertEquals(String actual, String expected) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	public static void assertEquals(boolean actual, boolean expected, String testcase) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}
	
	public static void assertEquals(boolean actual, boolean expected) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	
	public static void assertEquals(double actual, double expected, String testcase) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}
	
	public static void assertEquals(double actual, double expected) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	
	public static void assertEquals(Object actual, Object expected) {
		try {
			org.testng.Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	
	public static void fail(String message) {
		try {
			LOGGER.info("FAIL: " + message);
			org.testng.Assert.fail(message);
		} catch (Exception e) {
			LOGGER.warn("", e);
		}
	}
	
	public static void fail(String message, String testcase) {
		try {
			org.testng.Assert.fail(message);
			LOGGER.info("Failed " + testcase);
		} catch (Exception e) {
			LOGGER.warn(testcase, e);
		}
	}

	public static void assertTrue(boolean condition, String testcase) {
		try {
			org.testng.Assert.assertTrue(condition);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}
	
	public static void assertTrue(boolean condition) {
		try {
			org.testng.Assert.assertTrue(condition);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	
	public static void assertFalse(boolean condition, String testcase) {
		try {
			org.testng.Assert.assertFalse(condition);
			LOGGER.info(testcase + " Passed");
		} catch (Exception e) {
			LOGGER.warn(testcase + " Failed", e);
		}
	}
	
	public static void assertFalse(boolean condition) {
		try {
			org.testng.Assert.assertFalse(condition);
		} catch (Exception e) {
			LOGGER.warn("Assertion failed.", e);
		}
	}
	
	public static void assertNotEquals(Object actual, Object expected, String message) {
		try {
			org.testng.Assert.assertNotSame(actual, expected, message);
		} catch (Exception e) {
			LOGGER.info("Assertion failed.", e);
		}
	}
	
	public static void assertNull(Object object) {
		try {
			org.testng.Assert.assertNull(object);
		} catch (Exception e) {
			LOGGER.info("Assertion failed.", e);
		}
	}

	public static void assertNotNull(Object data) {
		try {
			org.testng.Assert.assertNotNull(data);
		} catch (Exception e) {
			LOGGER.warn("Assertion Failed.", e);
		}
	}
}
