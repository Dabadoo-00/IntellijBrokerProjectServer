package com.skills4testing.exchange.login.response;

import java.util.Vector;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CLoginResponse extends CMessage {

	/**
	 * This class is responsible for making Login response type message. if
	 * login request of the client succeeds then it makes this response. MDS
	 * will send SessionID to every user after logged in.
	 */

	// Constants Attribute of this class
	private final String kConnection = "Connection";
	private final String kLoginResponse = "LoginResponse";
	private final String kSessionID = "SessionID";
	private final String kRightsID = "RightsID";
	//private final String kAdministrator = "Administrator";
	//private final String kUser = "User";
	//private final String kLicensedForEDS = "LicensedForEDS";

	//private final int kAdminRightsID = 1;
	//private final int kUserRightsID = 2;

	// private attribute
	private String mSessionID;
	private Integer mRightsID;
	private boolean mEDSOption = false;

	// Default Constructor, set family and message type for every messages.
	public CLoginResponse() throws Exception {
		setFamily(kConnection);
		setMessageType(kLoginResponse);
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
	 * Return the XML String. it makes specific XML String for user's log in.
	 */
	public String toXML() throws Exception {
		// First, call super for general fields
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		// Now, add the specific fields for this message
		xmlResponse.append(super.makeXmlElement(kSessionID, mSessionID));
		xmlResponse.append(super.makeXmlElement(kRightsID, mRightsID));

		return xmlResponse.toString();
	}

	/**
	 * Return session ID for the user
	 */

	public String getSessionID() {
		return mSessionID;
	}

	/**
	 * set session ID for the user
	 * 
	 * @param sessionid
	 *            ID for the user's Session
	 */
	public void setSessionID(String sessionid) {
		mSessionID = sessionid;
	}

	/**
	 * Return rights ID for the user
	 */
	public Integer getRightsID() {
		return mRightsID;
	}

	/**
	 * set rights ID for the user
	 * 
	 * @param rightsID
	 *            for the user's Session
	 */
	public void setRightsID(Integer rightsid) {
		mRightsID = rightsid;
	}

	/**
	 * 
	 * @param status
	 */
	public void setEDSOption(boolean status) {
		this.mEDSOption = status;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsEDSOption() {
		return mEDSOption;
	}
}
