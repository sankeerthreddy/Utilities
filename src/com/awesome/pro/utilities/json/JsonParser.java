package com.awesome.pro.utilities.json;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

/**
 * Wrapper on GSON parser.
 * @author siddharth.s
 */
public class JsonParser {

	/**
	 * Root logger instance.
	 */
	private static Logger LOGGER = Logger.getLogger(JsonParser.class);

	/**
	 * Singleton instance.
	 */
	private static JsonParser INSTANCE = null;

	/**
	 * GSON instance.
	 */
	private Gson gson;

	/**
	 * Initialize JSON parser with adapters.
	 * @param deserializers Adapters for deserialization.
	 * @param serializersAdapters for serialization.
	 */
	public static void initialize(
			final Map<Class<?>, JsonDeserializer<Class<?>>> deserializers,
			final Map<Class<?>, JsonSerializer<Class<?>>> serializers) {
		if (INSTANCE == null) {
			synchronized (JsonParser.class) {
				if (INSTANCE == null) {
					INSTANCE = new JsonParser(deserializers, serializers);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("JSON parser initialized.");
					}
				}
			}
		}
		LOGGER.warn("JSON parser has been already initialized. Ignoring.");
	}

	public static JsonParser getInstance() {
		if (INSTANCE == null) {
			LOGGER.error("JSON parser has not been initialized yet.");
		}
		return INSTANCE;
	}

	/**
	 * Wrapper on GSON parser.
	 * @param deserializers Adapters for deserialization.
	 * @param serializers Adapters for serialization.
	 */
	private JsonParser(final Map<Class<?>, JsonDeserializer<Class<?>>> deserializers,
			final Map<Class<?>, JsonSerializer<Class<?>>> serializers) {
		GsonBuilder builder = new GsonBuilder();

		final Iterator<Entry<Class<?>, JsonDeserializer<Class<?>>>> iter1 =
				deserializers.entrySet().iterator();
		while (iter1.hasNext()) {
			Entry<Class<?>, JsonDeserializer<Class<?>>> deserializer = iter1.next();
			builder.registerTypeAdapter(deserializer.getKey(), deserializer.getValue());
		}

		final Iterator<Entry<Class<?>, JsonSerializer<Class<?>>>> iter2 =
				serializers.entrySet().iterator();
		while (iter2.hasNext()) {
			Entry<Class<?>, JsonSerializer<Class<?>>> serializer = iter2.next();
			builder.registerTypeAdapter(serializer.getKey(), serializer.getValue());
		}

		gson = builder.create();
	}

	/**
	 * @param in JSON string input.
	 * @return JSON element which can be converted to
	 * either a JSON object or an array depending on the
	 * input.
	 */
	public JsonElement parseJson(String in) {
		try {
			return this.gson.fromJson(in, JsonElement.class);
		} catch (RuntimeException e) {
			LOGGER.error("Unable to parse json: " + in, e);
			return null;
		}
	}

	/**
	 * @param in JSON object input string.
	 * @return Parsed JSON object.
	 */
	public JsonObject parseObject(String in) {
		JsonElement element = parseJson(in);
		if(element == null)
			return null;
		if(element.isJsonObject())
			return element.getAsJsonObject();
		LOGGER.warn("Input string is not a JSON object. Returning null.\n" + in);
		return null;
	}

	/**
	 * @param in JSON array input string.
	 * @return Parsed JSON array.
	 */
	public JsonArray parseArray(String in) {
		JsonElement element = parseJson(in);
		if(element.isJsonArray())
			return element.getAsJsonArray();
		LOGGER.warn("Input string is not a JSON array. Returning null.\n" + in);
		return null;
	}

	/**
	 * @param json JSON object.
	 * @param jsonPath Successive path of JSON to fetch
	 * e.g. e.f.v.ri
	 * @return Inner JSON object.
	 * Null is returned if any key doesn't exist.
	 * Null is returned if at any the element is an array
	 * and not an object.
	 * Null is returned if the last key doesn't represent
	 * an object.
	 */
	public JsonObject getInnerJSONObjectObject(JsonObject json, String jsonPath) {
		String[] path = jsonPath.split("[.]");
		int size = path.length;

		for(int i = 0; i < size; i ++) {
			String key = path[i];
			if(!json.has(key))
				return null;

			JsonElement jsonElement = json.get(key);
			if(!jsonElement.isJsonObject())
				return null;

			json = jsonElement.getAsJsonObject();
		}
		return json;
	}

	public <T> T fromJson(JsonElement json, Class<T> classOfT) {
		return this.gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(JsonElement json, Type typeOfT) {
		return this.gson.fromJson(json, typeOfT);
	}

	public <T> T fromJson(String json, Class<T> classOfT) {
		return this.gson.fromJson(json, classOfT);
	}

	public <T> T fromJson(String json, Type typeOfT) {
		return this.gson.fromJson(json, typeOfT);
	}

}
