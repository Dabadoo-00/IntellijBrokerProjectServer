package com.skills4testing.exchange.login.query;

import java.util.Vector;

import org.xml.sax.SAXException;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CPingQuery extends CMessage {
	/**
	 * This class contains the Ping message of the clients.
	 */
	// Constants Attribute of this class
	private final String kConnection = "Connection";
	private final String kPing = "Ping";
	private final String kSessionID = "SessionID";

	// private attribute
	private String mSessionID;

	// Default Constructor
	public CPingQuery() throws Exception {
		setFamily(kConnection);
		setMessageType(kPing);
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

	// Override methods of the DefaultHandler class
	// to gain notification of SAX Events and to parse the xml document.
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		super.endElement(namespaceURI, localName, qName);
		if (localName.equalsIgnoreCase(kSessionID)) {
			String sID = mContents.toString();
			this.setSessionID(sID);
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
		return xmlResponse.toString();
	}

	/**
	 * Return Session to ping
	 */
	public String getSessionID() {
		return mSessionID;

	}

	/**
	 * set Session ID to check authentication
	 * 
	 * @param user
	 *            User Name
	 */
	public void setSessionID(String sid) {
		this.mSessionID = sid;
	}

}
