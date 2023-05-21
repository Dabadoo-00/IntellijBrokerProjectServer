package com.skills4testing.exchange.login.response;

import java.util.Vector;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CLogoutResponse extends CMessage {
	/**
	 * This class is responsible for making Logout response message. if the user
	 * tries to logout then it makes this response.
	 */

	// Constants attribute of this class
	private final String kConnection = "Connection";
	private final String kLogoutResponse = "LogoutResponse";
	private final String kResult = "Result";

	// private attribute
	private String mResult;

	// Default Constructor, set family and message type for every messages.
	public CLogoutResponse() throws Exception {
		setFamily(kConnection);
		setMessageType(kLogoutResponse);
	}

	/**
	 * Register this message to Message Descriptor.
	 */
	public Vector<CMessageDescriptor> registerMessages() {
		Vector<CMessageDescriptor> registerVector = new Vector<CMessageDescriptor>();
		CMessageDescriptor msgDesc = new CMessageDescriptor(mFamily, mMessageType);
		registerVector.add(msgDesc);
		return registerVector;
	}

	/**
	 * Return the XML String. it makes specific XML String when user's logged
	 * out.
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
	 * set result ID for the user
	 * 
	 * @param result
	 *            of user's login
	 */
	public void setResult(String result) {
		this.mResult = result;
	}
}
