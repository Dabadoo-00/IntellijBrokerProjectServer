package com.skills4testing.core.message;


import java.util.*;
import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.SAXException;

import com.skills4testing.core.util.*;


public class CMessage extends DefaultHandler {

	/**
	 * This is the base class of messages between clients and server. all
	 * messages (Dynamically loadable) class will extends this class. it
	 * contains @param mFamily
	 * 
	 * @param message
	 *            . it contains fromXML method to recieve and parse the incoming
	 *            messages and toXML method to send the XML messages.
	 */
	protected String mFamily;
	protected String mMessageType;

	// The source component of the message sender like MPM, MDS
	protected String mSource = "Not Used";

	// The version of the message sender componenet like 1.5.60
	protected String mVersion = "Not Used";

	// protected String mSessionID;

	// parser
	protected XMLReader mXMLParser;
	// Buffer for Collecting data from the characters SAX event.
	protected CharArrayWriter mContents = new CharArrayWriter();

	// Constants Attribute of this class
	protected final String kFamily = "Family";
	protected final String kMessage = "Message";
	protected final String kSource = "Source";
	protected final String kVersion = "Version";
	// private final String kSessionID ="SessionID";
	protected static final String CRLF = "";
	private static final String SPACE = " ";
	private static final String EQUAL = "=";
	private static final String DOUBLE_QUOTE = "\"";
	private static final String SLASH = "/";

	private boolean isFault = false;
	
	private boolean mDebugOn = true;//CWICTCEServer.g_app.getPreferences().debugOn
	

	// Default Constructor
	public CMessage() throws Exception {
		// Create SAX 2 parser...
		try {

//			if (CConstants.kJDK14)
//				mXMLParser = XMLReaderFactory.createXMLReader(CConstants.kXMLDriverCrimson);
//			else
				mXMLParser = XMLReaderFactory.createXMLReader();
			// Set the ContentHandler...
			mXMLParser.setContentHandler(this);
		} catch (Exception e) {
			if (mDebugOn) {
				System.out.println("org.xml.sax.driver = "
						+ System.getProperty("org.xml.sax.driver"));
				System.out.println("CMessage constructor exception:");
				e.printStackTrace();

			}
			CError.throwError(800);
		}
	}

	/**
	 * return the CMessage type object from incoming XML message.
	 */
	public CMessage fromXML(String XMLString) throws Exception {
		if (null == XMLString) {
			if (mDebugOn) {
				System.out
						.println("WHY OH WHY?\r\nDo not pass me nothing. That is too rude...");
			}
			CError.throwError(803);
		}
		try {
			mXMLParser.parse(new InputSource(new StringReader(XMLString)));
		} catch (Exception e) {
			return this;// Added by Moniruzzaman on 12.04.2003
		}
		return this;
	}

	/**
	 * All message would be registered to the CMessageDescriptor class. Base
	 * classes of the CMessage will override this method to Register the class.
	 */
	public Vector<CMessageDescriptor> registerMessages() throws Exception {
		throw new Exception("Override this in the sub class");
	}

	// Override methods of the DefaultHandler class
	// to gain notification of SAX Events and to parse the xml document.
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attr) throws SAXException {
		mContents.reset();
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (true)
			if (localName.equalsIgnoreCase(kFamily)) {
				this.setFamily(mContents.toString());
			} else if (localName.equalsIgnoreCase(kMessage)) {
				// System.out.println(msgType);
				this.setMessageType(mContents.toString());
			} else if (localName.equalsIgnoreCase(kSource)) {
				this.setSource(mContents.toString());
			} else if (localName.equalsIgnoreCase(kSource)) {
				this.setVersion(mContents.toString());
			}
		// if ( localName.equalsIgnoreCase( kSessionID ) ) {
		// this.setSessionID(mContents.toString());
		// }

	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {

		mContents.write(ch, start, length);
	}

	/**
	 * Return the XML String.
	 */
	public String toXML() throws Exception {
		StringBuffer xmlResponse = new StringBuffer();
		try {
			xmlResponse.append(CMessage.makeXmlElement(kFamily, mFamily));
			xmlResponse.append(CMessage.makeXmlElement(kMessage, mMessageType));
			xmlResponse.append(CMessage.makeXmlElement(kSource, mSource));
			xmlResponse.append(CMessage.makeXmlElement(kVersion, mVersion));
			// xmlResponse.append(this.makeXmlElement(kSessionID,mSessionID));
		} catch (Exception e) {
			CError.throwError(801);
		}
		return xmlResponse.toString();
	}

	/**
	 * xmlEncode(String inString);
	 * 
	 * this method tests a String(inString) and encodes special characters of
	 * the String. If any special character(<, >, &, ', ") exists in the String,
	 * XML parser shows an error. this method replaces these characters by
	 * proper xml entities (i.e: <=&lt;, >=&gt;, &=&amps;, '=&apos, "=&quot;)
	 * 
	 * if there is no such character it returns the String as it is if the
	 * forTest is null it returns null
	 * 
	 */
	public static String xmlEncode(String inString) throws Exception {

		if (inString == null) {
			return null;
		}

		// Hacking
		inString = Util.StringSearchReplace(inString, "&", "&amp;"); // This
																		// should
																		// be
																		// the
																		// first
																		// replace
																		// character
		inString = Util.StringSearchReplace(inString, "<", "&lt;");
		inString = Util.StringSearchReplace(inString, ">", "&gt;");
		inString = Util.StringSearchReplace(inString, "\'", "&apos;");
		inString = Util.StringSearchReplace(inString, "\"", "&quot;");
		return inString;

	}

	/**
	 * makeXmlElement
	 * 
	 * makes xml element with tagName and tagValue
	 * 
	 * @param tagName
	 *            name of the element
	 * @param tagValue
	 *            value of the element
	 */
	public static String makeXmlElement(String tagName, int tagValue)
			throws Exception {
		tagName = tagName.trim();
		return MsgConst.kSTART_TAG_ON + tagName + MsgConst.kTAG_OF + tagValue
				+ MsgConst.kEND_TAG_ON + tagName + MsgConst.kTAG_OF + CRLF;
	}

	/**
	 * makeXmlElement
	 * 
	 * makes xml element with tagName and tagValue
	 * 
	 * @param tagName
	 *            name of the element
	 * @param tagValue
	 *            value of the element
	 */
	public static String makeXmlElement(String tagName, Integer tagValue)
			throws Exception {

		tagName = tagName.trim();
		if (tagValue == null)
			// It's definitely Empty
			return makeEmptyElement(tagName);
		else
			return MsgConst.kSTART_TAG_ON + tagName + MsgConst.kTAG_OF
					+ tagValue.intValue() + MsgConst.kEND_TAG_ON + tagName
					+ MsgConst.kTAG_OF + CRLF;

	}

	/**
	 * makeXmlElement
	 * 
	 * Previous method overidden for int type
	 * 
	 * @param tagName
	 *            name of the element
	 * @param tagValue
	 *            value of the element
	 */
	public static String makeXmlElement(String tagName, String tagValue)
			throws Exception {
		tagName = tagName.trim();
		if (tagValue == null)
			return makeEmptyElement(tagName);
		else {
			tagValue = tagValue.trim();
			return MsgConst.kSTART_TAG_ON + tagName + MsgConst.kTAG_OF
					+ xmlEncode(tagValue) + MsgConst.kEND_TAG_ON + tagName
					+ MsgConst.kTAG_OF + CRLF;
		}
	}

	/**
	 * makeEmptyElement
	 * 
	 * Previous method overidden for int type
	 * 
	 * @param tagName
	 *            name of the element
	 */
	public static String makeEmptyElement(String tagName) throws Exception {
		tagName = tagName.trim();
		return MsgConst.kSTART_TAG_ON + tagName + SLASH + MsgConst.kTAG_OF
				+ CRLF;
	}

	/**
	 * makeEmptyElementWithAttribute
	 * 
	 * Previous method overidden for int type
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
	public static String makeEmptyElementWithAttribute(String tagName,
			String attributeName, String attributeValue) throws Exception {
		tagName = tagName.trim();
		if (attributeValue == null)
			return makeEmptyElement(tagName);
		else {
			attributeValue = attributeValue.trim();
			return MsgConst.kSTART_TAG_ON + tagName + SPACE + attributeName
					+ EQUAL + DOUBLE_QUOTE + xmlEncode(attributeValue)
					+ DOUBLE_QUOTE + SLASH + MsgConst.kTAG_OF + CRLF;
		}

	}

	/**
	 * makeXmlStartTag
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 */
	public static String makeXmlStartTag(String tagName) throws Exception {
		tagName = tagName.trim();
		return MsgConst.kSTART_TAG_ON + tagName + MsgConst.kTAG_OF + CRLF;
	}

	/**
	 * makeXmlStartTagWithAttribute
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
	public static String makeXmlStartTagWithAttribute(String tagName,
			String attributeName, String attributeValue) throws Exception {
		tagName = tagName.trim();
		if (attributeValue == null)
			return makeXmlStartTag(tagName);
		else {
			attributeValue = attributeValue.trim();
			return MsgConst.kSTART_TAG_ON + tagName + SPACE + attributeName
					+ EQUAL + DOUBLE_QUOTE + xmlEncode(attributeValue)
					+ DOUBLE_QUOTE + MsgConst.kTAG_OF + CRLF;
		}
	}

	/**
	 * makeXmlStartTagWithAttribute
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
	public static String makeXmlWithAttribute(String tagName,
			String attributeName, String attributeValue, String value)
			throws Exception {
		if (value == null)
			return makeEmptyElementWithAttribute(tagName, attributeName,
					attributeValue);
		else {
			value = value.trim();
			attributeValue = attributeValue.trim();
			return MsgConst.kSTART_TAG_ON + tagName + SPACE + attributeName
					+ EQUAL + DOUBLE_QUOTE + xmlEncode(attributeValue)
					+ DOUBLE_QUOTE + MsgConst.kTAG_OF + xmlEncode(value)
					+ MsgConst.kEND_TAG_ON + tagName + MsgConst.kTAG_OF + CRLF;
		}
	}

	/**
	 * makeXmlStartTagWithAttribute
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
	public static String makeEmptyElementStartTagWithTwoAttribute(
			String pTagName, String pFirstAttributeName,
			String pFirstAttributeValue, String pSecondAttributeName,
			String pSecondAttributeValue) throws Exception {
		if (pFirstAttributeValue == null)
			return makeXmlStartTagWithAttribute(pTagName, pSecondAttributeName,
					pSecondAttributeValue);
		else if (pSecondAttributeValue == null)
			return makeXmlStartTagWithAttribute(pTagName, pSecondAttributeName,
					pSecondAttributeValue);
		else {
			pFirstAttributeValue = pFirstAttributeValue.trim();
			pSecondAttributeValue = pSecondAttributeValue.trim();
			return MsgConst.kSTART_TAG_ON + pTagName + SPACE
					+ pFirstAttributeName + EQUAL + DOUBLE_QUOTE
					+ xmlEncode(pFirstAttributeValue) + DOUBLE_QUOTE + SPACE
					+ pSecondAttributeName + EQUAL + DOUBLE_QUOTE
					+ xmlEncode(pSecondAttributeValue) + DOUBLE_QUOTE + SLASH
					+ MsgConst.kTAG_OF + CRLF;
		}
	}

	/**
	 * makeXmlStartTagWithAttribute
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
	public static String makeXmlStartTagWithTwoAttribute(String pTagName,
			String pFirstAttributeName, String pFirstAttributeValue,
			String pSecondAttributeName, String pSecondAttributeValue)
			throws Exception {
		if (pFirstAttributeValue == null)
			return makeXmlStartTagWithAttribute(pTagName, pSecondAttributeName,
					pSecondAttributeValue);
		else if (pSecondAttributeValue == null)
			return makeXmlStartTagWithAttribute(pTagName, pSecondAttributeName,
					pSecondAttributeValue);
		else {
			pFirstAttributeValue = pFirstAttributeValue.trim();
			pSecondAttributeValue = pSecondAttributeValue.trim();
			return MsgConst.kSTART_TAG_ON + pTagName + SPACE
					+ pFirstAttributeName + EQUAL + DOUBLE_QUOTE
					+ xmlEncode(pFirstAttributeValue) + DOUBLE_QUOTE + SPACE
					+ pSecondAttributeName + EQUAL + DOUBLE_QUOTE
					+ xmlEncode(pSecondAttributeValue) + DOUBLE_QUOTE
					+ MsgConst.kTAG_OF + CRLF;
		}
	}

	/**
	 * makeXmlEmplyTagWithTwoAttribute
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 * 
	 *            public static String makeXmlEmplyTagWithTwoAttribute(String
	 *            pTagName, String pFirstAttributeName, String
	 *            pFirstAttributeValue, String pSecondAttributeName, String
	 *            pSecondAttributeValue)throws Exception { return
	 *            MsgConst.kSTART_TAG_ON + pTagName + SPACE +
	 *            pFirstAttributeName + EQUAL + DOUBLE_QUOTE +
	 *            xmlEncode(pFirstAttributeValue) + DOUBLE_QUOTE + SPACE +
	 *            pSecondAttributeName + EQUAL + DOUBLE_QUOTE +
	 *            xmlEncode(pSecondAttributeValue) + DOUBLE_QUOTE
	 *            +SLASH+MsgConst.kTAG_OF+ CRLF; }
	 */

	/**
	 * makeXmlEndTag
	 * 
	 * creates xml start Tag like <tag_Name>
	 * 
	 * @param tagName
	 *            name of the element
	 */
	public static String makeXmlEndTag(String tagName) throws Exception {
		return MsgConst.kEND_TAG_ON + tagName + MsgConst.kTAG_OF + CRLF;
	}

	/**
	 * makeXmlElementAttrbute
	 * 
	 * makes xml element with tagName, tagValue, attribute Name, attribute value
	 * 
	 * @param tagName
	 *            name of the element
	 * @param tagValue
	 *            value of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
//	private String makeXmlElementWithAttribute(String tagName, String tagValue,
//			String attributeName, String attributeValue) throws Exception {
//		if (tagValue == null)
//			return makeEmptyElementWithAttribute(tagName, attributeName,
//					attributeValue);
//		else {
//			tagValue = tagValue.trim();
//			attributeValue = attributeValue.trim();
//			return MsgConst.kSTART_TAG_ON + tagName + SPACE + attributeName
//					+ EQUAL + DOUBLE_QUOTE + xmlEncode(attributeValue)
//					+ DOUBLE_QUOTE + MsgConst.kTAG_OF + xmlEncode(tagValue)
//					+ MsgConst.kEND_TAG_ON + tagName + MsgConst.kTAG_OF + CRLF;
//		}
//	}

	/**
	 * makeXmlElementAttrbut
	 * 
	 * Previous method overridden for int type values. makes xml element with
	 * tagName, tagValue, attribute Name, attribute value
	 * 
	 * @param tagName
	 *            name of the element
	 * @param tagValue
	 *            value of the element
	 * @param attributeName
	 *            value of the element
	 * @param attributeValue
	 *            value of the attbute
	 */
//	private String makeXmlElementWithAttribute(String pTagName, int tagValue,
//			String attributeName, int attributeValue) throws Exception {
//		return MsgConst.kSTART_TAG_ON + pTagName + SPACE + attributeName
//				+ EQUAL + DOUBLE_QUOTE + attributeValue + DOUBLE_QUOTE
//				+ MsgConst.kTAG_OF + tagValue + MsgConst.kEND_TAG_ON + pTagName
//				+ MsgConst.kTAG_OF + CRLF;
//	}

	/**
	 * returns family name of the message.
	 */
	public String getFamily() {
		return mFamily;
	}

	/**
	 * Set family name of the message.
	 * 
	 * @param familyName
	 *            Name of the family
	 */

	public void setFamily(String familyName) {
		this.mFamily = familyName;
	}

	/**
	 * returns message type of the message.
	 */
	public String getMessageType() {
		return mMessageType;
	}

	/**
	 * set message type of the message.
	 * 
	 * @param messageType
	 *            type of the message
	 */
	public void setMessageType(String messageType) {
		this.mMessageType = messageType;
	}

	/**
	 * returns source of the message like MPM, MCIC etc.
	 */
	public String getSource() {
		return mSource;
	}

	/**
	 * set source type of the message.
	 * 
	 * @param SourceType
	 *            of the message
	 */
	public void setSource(String sourceType) {
		this.mSource = sourceType;
	}

	/**
	 * returns version the message sender.
	 */
	public String getVersion() {
		return this.mVersion;
	}

	/**
	 * set version of the message sender.
	 * 
	 * @param version
	 *            of the message sender.
	 */
	public void setVersion(String version) {
		this.mVersion = version;
	}

	/**
	 * 
	 * @return
	 */
	public boolean getIsFault() {
		return isFault;
	}

	/**
	 * 
	 * @param fault
	 */
	public void setIsFault(boolean fault) {
		isFault = fault;
	}

	// /**
	// * Get session ID
	// */
	// public String getSessionID(){
	// return mSessionID;
	// }
	//
	//
	// /**
	// * Set session ID
	// */
	// public void setSessionID(String sessionID){
	// this.mSessionID= sessionID;
	// }

}