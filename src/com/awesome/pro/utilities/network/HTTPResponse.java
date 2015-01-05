package com.awesome.pro.utilities.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * Represents a network call transaction, both the
 * request as well as the response.
 * @author siddharth.s
 */
public class HTTPResponse implements IHTTPResponse, IHTTPPerformance {

	/**
	 * Root logger instance.
	 */
	private static Logger LOGGER = Logger.getLogger(HTTPResponse.class);

	/**
	 * Map of response headers.
	 */
	private Map<String, String> headers;

	/**
	 * Status code of the HTTP response.
	 */
	private int status;

	/**
	 * HTTP request corresponding to the response.
	 */
	private IHTTPRequest request;

	/**
	 * Raw response text of the network call.
	 */
	private String rawResponse;

	/**
	 * Performance statistics.
	 */
	private IHTTPPerformance performance;

	/**
	 * HTTP response constructor.
	 * @param request HTTP request corresponding to the HTTP response.
	 * @param headers Map of HTTP headers.
	 * @param status Status code of the response.
	 */
	public HTTPResponse(IHTTPRequest request, Map<String, String> headers, int status, String rawResponse) {
		this.request = request;
		this.headers = headers;
		this.status = status;
		this.rawResponse = rawResponse;
	}

	/**
	 * HTTP response constructor.
	 * @param connection HTTP connection object which can be
	 * obtained while constructing a network call using the
	 * Java URL API.
	 */
	public HTTPResponse(HttpURLConnection connection) {
		//FIXME: Capture POST parameters.
		this.request = HTTPFactory.getHTTPRequest(connection.getURL().toString(),
				connection.getRequestMethod(), null, null);

		try {
			this.status = connection.getResponseCode();
		} catch (IOException e) {
			LOGGER.error("Error obtaining response code.", e);
			throw new Error("Error obtaining response code.", e);
		}
		populateHeaders(connection.getHeaderFields());

		InputStream is = null;
		try {
			is = connection.getInputStream();
		} catch (IOException e) {
			LOGGER.error("Unable to read response.", e);
			System.out.println("ERROR: Unable to read response.");
			System.exit(1);
		}
		this.rawResponse = convertStream(is, 1024);
	}

	/**
	 * Populate headers class variable.
	 * @param headers Map of headers obtained from URL API.
	 */
	private void populateHeaders(Map<String, List<String>> headers) {
		this.headers = new HashMap<>();
		Iterator<Entry<String, List<String>>> iter = headers.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<String, List<String>> entry = iter.next();

			String key = entry.getKey();
			List<String> value = entry.getValue();

			int size = value.size();

			if(size == 0) {
				this.headers.put(key, "");
				continue;
			}

			if(size == 1) {
				this.headers.put(key, value.get(0));
				continue;
			}

			String val = "";
			for(int i = 0; i < size; i ++)
				val = val + value.get(i) + NetworkReferences.COOKIE_SEPARATOR;
			val = val.substring(0, val.length() - 1);
			this.headers.put(key, val);
		}
	}

	/**
	 * @return HTTP status code of the HTTP response.
	 */
	@Override
	public int getStatusCode() {
		return this.status;
	}

	/**
	 * @return Map of all the HTTP response headers.
	 */
	@Override
	public Map<String, String> getAllResponseHeaders() {
		return this.headers;
	}

	/**
	 * @param key HTTP response header key.
	 * @return HTTP response header value of the specified key.
	 */
	@Override
	public String getResponseHeaderByKey(String key) {
		if(this.headers.containsKey(key))
			return this.headers.get(key);
		return null;
	}

	/**
	 * @return URL to which the request was made.
	 */
	@Override
	public String getBaseURL() {
		return this.request.getBaseURL();
	}

	/**
	 * @return Map of all request parameters.
	 */
	@Override
	public Map<String, String> getAllRequestParameters() {
		return this.request.getAllParameters();
	}

	/**
	 * @param key Request parameter key.
	 * @return Parameter value corresponding to the key.
	 */
	@Override
	public String getRequestParameterByKey(String key) {
		return this.request.getRequestParameterByKey(key);
	}

	/**
	 * @return HTTP method of the call - GET, PUT, POST etc.
	 */
	@Override
	public String getMethod() {
		return this.request.getMethod();
	}

	/**
	 * @return HTTP protocol over which the request was made.
	 * HTTP or HTTPS.
	 */
	@Override
	public String getProtocol() {
		return this.request.getProtocol();
	}

	/**
	 * @return Map of all the request HTTP headers.
	 */
	@Override
	public Map<String, String> getAllRequestHeaders() {
		return this.request.getAllHeaders();
	}

	/**
	 * @param key HTTP request header key.
	 * @return HTTP request header value of the specified key.
	 */
	@Override
	public String getRequestHeaderByKey(String key) {
		return this.request.getHeaderByKey(key);
	}

	/**
	 * @return Raw response text of the network call.
	 */
	@Override
	public String getRawResponse() {
		return this.rawResponse;
	}

	/**
	 * @param is Input stream.
	 * @param bufferSize Buffer size to be used while reading.
	 * @return String read from input stream.
	 */
	private static String convertStream(InputStream is, int bufferSize)	{
		final char[] buffer = new char[bufferSize];
		final StringBuilder out = new StringBuilder();
		try {
			final Reader in = new InputStreamReader(is, "UTF-8");
			try {
				for (;;) {
					int rsz = in.read(buffer, 0, buffer.length);
					if (rsz < 0)
						break;
					out.append(buffer, 0, rsz);
				}
			}
			finally {
				in.close();
			}
		}
		catch (UnsupportedEncodingException e) {
			LOGGER.error("Unsupported encoding.", e);
			throw new Error("Unsupported encoding.");
		}
		catch (IOException e) {
			LOGGER.error("Unable to read input stream.", e);
			throw new Error("ERROR: Unable to read input stream.");
		}
		return out.toString();
	}

	@Override
	public String toString() {
		return "Request: \n" + this.request
				+ "\nResponse Code: " + this.status
				+ "\nContent Length: "
				+ (this.rawResponse == null ? 0 : this.rawResponse.length()) + "\n";
	}

	@Override
	public Date getClientBeginRequest() {
		return performance.getClientBeginRequest();
	}

	@Override
	public Date getClientBeginResponse() {
		return performance.getClientBeginResponse();
	}

	@Override
	public Date getClientConnected() {
		return performance.getClientConnected();
	}

	@Override
	public Date getClientDoneRequest() {
		return performance.getClientDoneRequest();
	}

	@Override
	public Date getClientDoneResponse() {
		return performance.getClientDoneResponse();
	}

	@Override
	public Date getFiddlerBeginRequest() {
		return performance.getFiddlerBeginRequest();
	}

	@Override
	public Date getFiddlerGotRequestHeaders() {
		return performance.getFiddlerGotRequestHeaders();
	}

	@Override
	public Date getFiddlerGotResponseHeaders() {
		return performance.getFiddlerGotResponseHeaders();
	}

	@Override
	public Date getServerBeginResponse() {
		return performance.getServerBeginResponse();
	}

	@Override
	public Date getServerConnected() {
		return performance.getServerConnected();
	}

	@Override
	public Date getServerDoneResponse() {
		return performance.getServerDoneResponse();
	}

	@Override
	public Date getServerGotRequest() {
		return performance.getServerGotRequest();
	}

	@Override
	public int getDNSTime() {
		return performance.getDNSTime();
	}

	@Override
	public int getGatewayDeterminationTime() {
		return performance.getGatewayDeterminationTime();
	}

	@Override
	public int getHTTPSHandshakeTime() {
		return performance.getHTTPSHandshakeTime();
	}

	@Override
	public int getTCPConnectTime() {
		return performance.getTCPConnectTime();
	}

}
