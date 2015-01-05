package com.awesome.pro.utilities.network;

import java.util.Map;

/**
 * Interface for HTTP request object.
 * @author siddharth.s
 */
public interface IHTTPRequest {
	
	/**
	 * @return Base URL of the request.
	 */
	public String getBaseURL();

	/**
	 * @return HTTP method of the request.
	 */
	public String getMethod();

	/**
	 * @return HTTP protocol of the request.
	 */
	public String getProtocol();

	/**
	 * @return Map of all request headers.
	 */
	public Map<String, String> getAllHeaders();

	/**
	 * @param key HTTP header key
	 * @return HTTP header value of the specified key.
	 */
	public String getHeaderByKey(String key);

	/**
	 * @return Map of all request parameters.
	 */
	public Map<String, String> getAllParameters();

	/**
	 * @param key Request parameter key.
	 * @return Parameter value corresponding to the key.
	 */
	public String getRequestParameterByKey(String key);

}
