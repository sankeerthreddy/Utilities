package com.awesome.pro.utilities.network;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * This class contains details about web performance and connection
 * time lines.
 * @author siddharth.s
 */
public class HTTPPerformance implements IHTTPPerformance {

	private String ClientBeginRequest;
	private String ClientBeginResponse;
	private String ClientConnected;
	private String ClientDoneRequest;
	private String ClientDoneResponse;
	private String FiddlerBeginRequest;
	private String FiddlerGotRequestHeaders;
	private String FiddlerGotResponseHeaders;
	private String ServerBeginResponse;
	private String ServerConnected;
	private String ServerDoneResponse;
	private String ServerGotRequest;
	private int DNSTime;
	private int GatewayDeterminationTime;
	private int HTTPSHandshakeTime;
	private int TCPConnectTime;

	/**
	 * Format to parse dates like 10-Jun-14 5:37:12 PM.
	 */
	private final static SimpleDateFormat DATE_FORMAT =
			new SimpleDateFormat("dd-MMM-yy h:mm:ss aa");

	/**
	 * Root logger instance.
	 */
	private static final Logger LOGGER = Logger.getRootLogger();

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getClientBeginRequest()
	 */
	@Override
	public Date getClientBeginRequest() {
		return parseDate(ClientBeginRequest);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getClientBeginResponse()
	 */
	@Override
	public Date getClientBeginResponse() {
		return parseDate(ClientBeginResponse);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getClientConnected()
	 */
	@Override
	public Date getClientConnected() {
		return parseDate(ClientConnected);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getClientDoneRequest()
	 */
	@Override
	public Date getClientDoneRequest() {
		return parseDate(ClientDoneRequest);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getClientDoneResponse()
	 */
	@Override
	public Date getClientDoneResponse() {
		return parseDate(ClientDoneResponse);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getFiddlerBeginRequest()
	 */
	@Override
	public Date getFiddlerBeginRequest() {
		return parseDate(FiddlerBeginRequest);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getFiddlerGotRequestHeaders()
	 */
	@Override
	public Date getFiddlerGotRequestHeaders() {
		return parseDate(FiddlerGotRequestHeaders);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getFiddlerGotResponseHeaders()
	 */
	@Override
	public Date getFiddlerGotResponseHeaders() {
		return parseDate(FiddlerGotResponseHeaders);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getServerBeginResponse()
	 */
	@Override
	public Date getServerBeginResponse() {
		return parseDate(ServerBeginResponse);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getServerConnected()
	 */
	@Override
	public Date getServerConnected() {
		return parseDate(ServerConnected);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getServerDoneResponse()
	 */
	@Override
	public Date getServerDoneResponse() {
		return parseDate(ServerDoneResponse);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getServerGotRequest()
	 */
	@Override
	public Date getServerGotRequest() {
		return parseDate(ServerGotRequest);
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getDNSTime()
	 */
	@Override
	public int getDNSTime() {
		return DNSTime;
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getGatewayDeterminationTime()
	 */
	@Override
	public int getGatewayDeterminationTime() {
		return GatewayDeterminationTime;
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getHTTPSHandshakeTime()
	 */
	@Override
	public int getHTTPSHandshakeTime() {
		return HTTPSHandshakeTime;
	}

	/* (non-Javadoc)
	 * @see com.tfsc.ilabs.core.network.impl.IHTTPPerformance#getTCPConnectTime()
	 */
	@Override
	public int getTCPConnectTime() {
		return TCPConnectTime;
	}

	/**
	 * @param string Date in string format.
	 * @return Parsed data object.
	 */
	private Date parseDate(String string) {
		if (string == null) {
			return null;
		}

		try {
			return DATE_FORMAT.parse(string);
		} catch (ParseException e) {
			LOGGER.warn("Unable to parse date: " + string, e);
			return null;
		}
	}
}
