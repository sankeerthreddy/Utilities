package com.awesome.pro.utilities.network;

import java.util.Date;

public interface IHTTPPerformance {

	/**
	 * @return the clientBeginRequest
	 */
	public Date getClientBeginRequest();

	/**
	 * @return the clientBeginResponse
	 */
	public Date getClientBeginResponse();

	/**
	 * @return the clientConnected
	 */
	public Date getClientConnected();

	/**
	 * @return the clientDoneRequest
	 */
	public Date getClientDoneRequest();

	/**
	 * @return the clientDoneResponse
	 */
	public Date getClientDoneResponse();

	/**
	 * @return the fiddlerBeginRequest
	 */
	public Date getFiddlerBeginRequest();

	/**
	 * @return the fiddlerGotRequestHeaders
	 */
	public Date getFiddlerGotRequestHeaders();

	/**
	 * @return the fiddlerGotResponseHeaders
	 */
	public Date getFiddlerGotResponseHeaders();

	/**
	 * @return the serverBeginResponse
	 */
	public Date getServerBeginResponse();

	/**
	 * @return the serverConnected
	 */
	public Date getServerConnected();

	/**
	 * @return the serverDoneResponse
	 */
	public Date getServerDoneResponse();

	/**
	 * @return the serverGotRequest
	 */
	public Date getServerGotRequest();

	/**
	 * @return the dNSTime
	 */
	public int getDNSTime();

	/**
	 * @return the gatewayDeterminationTime
	 */
	public int getGatewayDeterminationTime();

	/**
	 * @return the hTTPSHandshakeTime
	 */
	public int getHTTPSHandshakeTime();

	/**
	 * @return the tCPConnectTime
	 */
	public int getTCPConnectTime();

}