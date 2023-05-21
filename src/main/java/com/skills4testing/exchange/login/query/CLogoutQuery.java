package com.skills4testing.exchange.login.query;

import java.util.Vector;




import org.xml.sax.SAXException;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CLogoutQuery extends CMessage {
	/**
	 * This class contains the Logout message of the clients.
	 */

	// Constants Attribute of this class
	private final String kConnection = "Connection";
	private final String kLogout = "Logout";
	private final String kSessionID = "SessionID";
	private final String kReason = "Reason";
	private String mSessionID;
	private String mReason;

	// Default Constructor
	public CLogoutQuery() throws Exception {
		setFamily(kConnection);
		setMessageType(kLogout);
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

	// Override methods of the DefaultHandler class
	// to gain notification of SAX Events and to parse the xml document.

	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		super.endElement(namespaceURI, localName, qName);
		if (localName.equalsIgnoreCase(kSessionID)) {
			// System.out.println(user);
			this.setSessionID(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kReason)) {
			this.setReason(mContents.toString());
		}
	}

	/**
	 * Return the XML String. for Request type message it is not essential to
	 * implement
	 */
	public String toXML(String XMLString) throws Exception {
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		xmlResponse.append("<" + kSessionID + ">" + super.xmlEncode(mSessionID)
				+ "</" + kSessionID + ">" + CRLF);
		xmlResponse.append("<" + kReason + ">" + super.xmlEncode(mReason)
				+ "</" + kReason + ">" + CRLF);
		return xmlResponse.toString();
	}

	/**
	 * Return Session ID
	 */
	public String getSessionID() {
		return mSessionID;
	}

	/**
	 * set session ID
	 * 
	 * @param sessionID
	 *            ID of User's session
	 */
	public void setSessionID(String sessionID) {
		this.mSessionID = sessionID;
	}

	/**
	 * Return reason of log out
	 */
	public String getReason() {
		return mReason;
	}

	/**
	 * set reason of log out
	 * 
	 * @param reason
	 *            reason of logout
	 */

	public void setReason(String reason) {
		this.mReason = reason;
	}

}
