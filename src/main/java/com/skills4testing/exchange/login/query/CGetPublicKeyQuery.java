package com.skills4testing.exchange.login.query;

import java.util.Vector;

import org.xml.sax.SAXException;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CGetPublicKeyQuery extends CMessage {

	/**
	 * This class requests for public key of the server
	 */

	private String mUser;

	// Constants Attribute of this class
	private final String kCrypto = "Crypto";
	private final String kGetPublicKey = "GetPublicKey";
	private final String kUser = "User";

	// Default Constructor
	public CGetPublicKeyQuery() throws Exception {
		setFamily(kCrypto);
		setMessageType(kGetPublicKey);
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
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		super.endElement(namespaceURI, localName, qName);
		if (localName.equalsIgnoreCase(kUser)) {
			String user = mContents.toString();
			this.setUser(user);
		}
	}

	/**
	 * Return the XML String. for Request type message it is not essential to
	 * implement
	 */
	public String toXML() throws Exception {
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		xmlResponse.append("<" + kUser + ">" + super.xmlEncode(mUser) + "</"
				+ kUser + ">" + CRLF);
		return xmlResponse.toString();
	}

	/**
	 * Return user name to log in
	 */
	public String getUser() {
		return mUser;

	}

	/**
	 * set user name to log in
	 * 
	 * @param user
	 *            User Name
	 */
	public void setUser(String user) {
		this.mUser = user;
	}
}
