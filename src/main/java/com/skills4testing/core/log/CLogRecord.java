package com.skills4testing.core.log;

import java.util.*;

import com.skills4testing.core.util.*;

public class CLogRecord {

	private final String CRLF = "\r\n";

	private String date;

	private String clientIP;

	private String eventPerformed;

	/* empty Constructor */
	public CLogRecord() {
	}

	/**
	 * Constructor with one parameter
	 * 
	 * @param eventPerformed
	 *            event handled by the server
	 */
	public CLogRecord(String eventPerformed) {
		date = CDateTime.toSystemOutDate();
		this.clientIP = "";
		this.eventPerformed = eventPerformed;
	}

	/**
	 * constructor with two parameters
	 **/
	public CLogRecord(String clientIP, String eventPerformed) {
		date = CDateTime.toSystemOutDate();
		this.clientIP = clientIP;
		this.eventPerformed = eventPerformed;
	}

	/* constructor with all the parameters */
	/*
	 * public CLogRecord(String date, String time, String clientIP, String
	 * actionPerformed){ this.date = date; this.time = time; this.clientIP =
	 * clientIP; this.actionPerformed = actionPerformed; }
	 */

	/* setting clients ip address */
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	/* retrieve clients IP address */
	public String getClientIP() {
		return clientIP;
	}

	/* setting action performed string */
	public void setEventPerformed(String eventPerformed) {
		this.eventPerformed = eventPerformed;
	}

	/* retrieve action performed string */
	public String getEventPerformed() {
		return eventPerformed;
	}

	/* setting current date from server's date setting */
	public String currentDate() {
		date = CDateTime.toSystemOutDate();
		return date;
	}

	/* get date value */
	public String getDate() {
		return date;
	}

	/* convert it to message format */
	public String toString() {

		return getDate() + ":" + getClientIP() + ":" + getEventPerformed()
				+ CRLF;
	}

	/* constructing log record from a string */
	public void fromString(String logString) {

		StringTokenizer st = new StringTokenizer(logString);

		while (st.hasMoreTokens()) {
			date = st.nextToken("#");
			clientIP = st.nextToken("#");
			eventPerformed = st.nextToken("#");
		}
	}
}
