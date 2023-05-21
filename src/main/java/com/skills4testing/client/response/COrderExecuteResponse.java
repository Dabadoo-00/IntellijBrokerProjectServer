package com.skills4testing.client.response;

import java.util.Vector;

import org.xml.sax.SAXException;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;
import com.skills4testing.core.message.MsgConst;

public class COrderExecuteResponse extends CMessage {

	
	/**
	 * This class contains the Login message of the clients.
	 */

	private String mUser;
	private String mPassword;
	private String mVersion;

	// Constants Attribute of this class
	
	private final String kUser = "UserName"; // Modified from 'User' to			// 'UserName' from R1.5
	private final String kPassword = "Password";
	private final String kVersion = "Version";

	// Default Constructor
	public COrderExecuteResponse() throws Exception {
		setFamily(MsgConst.kExecuteOrderFamily);
		setMessageType(MsgConst.kExecuteResponse);
	}

	/**
	 * Register this message to Message Descriptor.
	 */
	@Override
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
		if (localName.equalsIgnoreCase(kUser)) {
			this.setUser(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kPassword)) {
			this.setPassword(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kVersion)) {
			this.setVersion(mContents.toString());
		}
	}

	/**
	 * Return the XML String. for Request type message it is not essential to
	 * implement
	 */
	public String toXML() throws Exception {
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		xmlResponse.append("<" + kUser + ">" + super.xmlEncode(mUser) + "</" + kUser + ">" + CRLF);
		xmlResponse.append("<" + kPassword + ">" + super.xmlEncode(mPassword) + "</" + kPassword + ">" + CRLF);
		xmlResponse.append("<" + kVersion + ">" + super.xmlEncode(mVersion) + "</" + kVersion + ">" + CRLF);
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

	/**
	 * Return password to log in
	 */
	public String getPassword() {
		return mPassword;
	}

	/**
	 * set password to log in
	 * 
	 * @param password
	 *            password to login
	 */
	public void setPassword(String password) {
		this.mPassword = password;
	}

	/**
	 * Get MPM Version
	 */
	public String getVersion() {
		return mVersion;
	}

	/**
	 * @param version
	 *            MPM Version
	 */
	public void setVersion(String version) {
		this.mVersion = version;
	}
}
