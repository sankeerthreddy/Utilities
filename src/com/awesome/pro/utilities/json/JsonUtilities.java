package com.awesome.pro.utilities.json;

import org.apache.log4j.Logger;

import com.awesome.pro.utilities.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtilities {

	private static Logger LOGGER = Logger.getLogger(JsonUtilities.class);

	/**
	 * Do check for null returns wherever it is used
	 * @param expression JSON path of the variable
	 * @param eventJson JSON object contained in the event
	 * @return String value of the specified variable
	 */	
	public static String getStringVariableValue(String expression, JsonObject eventJson) {
		char JSON_PATH_SEPARATOR = '.';
		String[] keys = StringUtils.split(expression, JSON_PATH_SEPARATOR);

		if(keys.length == 1) {
			if(eventJson.has(keys[0])) {
				JsonElement returnVal = eventJson.get(keys[0]);

				if(!returnVal.isJsonPrimitive()) {
					LOGGER.warn("Casting a non Json primitive into String: " + expression);
					String retVal = returnVal.toString();
					if(retVal.length() == 0)
						return null;
					return retVal;
				}

				String retVal = returnVal.getAsString();
				if(retVal.length() == 0)
					return null;

				return retVal;
			}
			else {
				LOGGER.warn("Variable not found in event JSON: " + expression + " - " + eventJson);
				return null;
			}
		}

		for(int i = 0; i < keys.length - 1; i ++) {
			if(eventJson.has(keys[i])) {
				JsonElement element = eventJson.get(keys[i]);
				if (element.isJsonObject())
					eventJson = element.getAsJsonObject();
				else
					throw new Error("Logic error.");
			}
			else {
				LOGGER.warn("Variable not found in event JSON: " + expression + " - " + eventJson);
				return null;
			}
		}

		if(eventJson.has(keys[keys.length - 1])) {
			JsonElement returnVal = eventJson.get(keys[keys.length - 1]);

			if(!returnVal.isJsonPrimitive())
				LOGGER.warn("Casting a non Json primitive into String: " + expression);

			String retVal = returnVal.toString();

			if(retVal.length() == 0)
				return null;

			return retVal;
		}
		else {
			LOGGER.warn("Variable not found in event JSON: " + expression + " - " + eventJson);
			return null;
		}
	}

	/**
	 * @param json JSON object to be analyzed.
	 * @return Set of all possible JSON paths. A JSON path will point to
	 * a primitive type.
	 */
	//TODO:
	/*
	public static final Set<String> getAllJsonPaths(final JsonObject json) {
		Set<String> paths = new HashSet<>();
		Set<Entry<String, JsonElement>> entries = json.entrySet();

		Iterator<Entry<String, JsonElement>> iter = entries.iterator();
		while (iter.hasNext()) {
			Entry<String, JsonElement> entry = iter.next();
			
		}
	}
	*/

}
