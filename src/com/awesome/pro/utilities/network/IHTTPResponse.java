package com.awesome.pro.utilities.network;

import java.util.Map;

/**
 * Interface for HTTP response object.
 * @author siddharth.s
 */
public interface IHTTPResponse {
	
	/**
	 * @return HTTP status code of the HTTP response.
	 */
	public int getStatusCode();
	
	/**
	 * @return Map of all the HTTP response headers.
	 */
	public Map<String, String> getAllResponseHeaders();
	
	/**
	 * @param key HTTP response header key.
	 * @return HTTP response header value of the specified key.
	 */
	public String getResponseHeaderByKey(String key);
	
	/**
	 * @return URL to which the request was made.
	 */
	public String getBaseURL();
	
	/**
	 * @return Map of all request parameters.
	 */
	public Map<String, String> getAllRequestParameters();
	
	/**
	 * @param key Request parameter key.
	 * @return Parameter value corresponding to the key.
	 */
	public String getRequestParameterByKey(String key);
	
	/**
	 * @return HTTP method of the call - GET, PUT, POST etc.
	 */
	public String getMethod();
	
	/**
	 * @return HTTP protocol over which the request was made.
	 * HTTP or HTTPS.
	 */
	public String getProtocol();
	
	/**
	 * @return Map of all the request HTTP headers.
	 */
	public Map<String, String> getAllRequestHeaders();
	
	/**
	 * @param key HTTP request header key.
	 * @return HTTP request header value of the specified key.
	 */
	public String getRequestHeaderByKey(String key);
	
	/**
	 * @return Raw response text of the network call.
	 */
	public String getRawResponse();

}
