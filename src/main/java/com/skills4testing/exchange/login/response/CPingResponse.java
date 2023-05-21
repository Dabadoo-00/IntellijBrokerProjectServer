package com.skills4testing.exchange.login.response;

import java.util.Vector;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CPingResponse extends CMessage {
	/**
	 * This class is responsible for making Ping response type message. if
	 * client tries to ping the MDS then it makes this response.
	 */

	// Constants Attribute of this class
	private final String kConnection = "Connection";
	private final String kPingResponse = "PingResponse";
	private final String kResult = "Result";

	// private attribute
	private String mResult;

	// Default Constructor, set family and message type for every messages.
	public CPingResponse() throws Exception {
		setFamily(kConnection);
		setMessageType(kPingResponse);
	}

	/**
	 * Register this message to Message Descriptor.
	 */
	public Vector<CMessageDescriptor> registerMessages() {
		Vector<CMessageDescriptor> registerVector = new Vector<CMessageDescriptor>();
		CMessageDescriptor msgDesc = new CMessageDescriptor(mFamily,mMessageType);
		registerVector.add(msgDesc);
		return registerVector;
	}

	/**
	 * Return the XML String. it makes specific XML String for user's ping.
	 */
	public String toXML() throws Exception {
		// First, call super for general fields
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		// Now, add the specific fields for this message
		xmlResponse.append("<" + kResult + ">" + super.xmlEncode(mResult)
				+ "</" + kResult + ">" + CRLF);
		return xmlResponse.toString();
	}

	/**
	 * Return result for the user
	 */
	public String getResult() {
		return mResult;
	}

	/**
	 * set result for the user
	 * 
	 * @param result
	 *            for the user's ping
	 */
	public void setResult(String Result) {
		this.mResult = Result;
	}
}
