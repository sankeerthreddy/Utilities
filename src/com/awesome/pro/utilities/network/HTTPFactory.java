package com.awesome.pro.utilities.network;

import java.net.HttpURLConnection;
import java.util.Map;

/**
 * Maps the HTTP request and response interfaces to their
 * respective implementations.
 * @author siddharth.s
 */
public class HTTPFactory {

	/**
	 * @return Instance of IHTTPCallActions.
	 */
	public static IHTTPCallActions getHTTPCallActionsInstance() {
		return HTTPCallActions.getInstance();
	}

	/**
	 * @param url URL of the request.
	 * @param method HTTP method of the request.
	 * @param headers Map of request headers.
	 * @return IHTTPRequest implementation.
	 */
	public static IHTTPRequest getHTTPRequest(String url, String method, Map<String, String> headers, Map<String, String> postData) {
		return new HTTPRequest(url, method, headers, postData);
	}

	/**
	 * @param request HTTP request corresponding to the HTTP response.
	 * @param headers Map of HTTP headers.
	 * @param status Status code of the response.
	 * @return IHTTPResponse implementation.
	 */
	public static IHTTPResponse getHTTPResponse(IHTTPRequest request, Map<String, String> headers, int status, String rawResponse) {
		return new HTTPResponse(request, headers, status, rawResponse);
	}

	/**
	 * @param connection HTTP connection object which can be
	 * obtained while constructing a network call using the
	 * Java URL API.
	 * @return IHTTPResponse implementation.
	 */
	public static IHTTPResponse getHTTPResponse(HttpURLConnection connection) {
		return new HTTPResponse(connection);
	}

	/**
	 * @return Class reference to implementation of HTTP request.
	 */
	public static Class<? extends IHTTPRequest> getHTTPRequestImplClass() {
		return HTTPRequest.class;
	}

	/**
	 * @return Class reference to implementation of HTTP response.
	 */
	public static Class<? extends IHTTPResponse> getHTTPResponseImplClass() {
		return HTTPResponse.class;
	}

	/**
	 * @return Class reference to implementation of HTTP performance.
	 */
	public static Class<? extends IHTTPPerformance> getHTTPPerformanceImplClass() {
		return HTTPPerformance.class;
	}

}
