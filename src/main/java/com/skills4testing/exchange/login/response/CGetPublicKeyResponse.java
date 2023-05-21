package com.skills4testing.exchange.login.response;

import java.util.Vector;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;

public class CGetPublicKeyResponse extends CMessage {

	/**
	 * This class is responsible for making GetPublicKey response with the
	 * public key of the server.
	 */

	// Constants Attribute of this class
	private final String kCrypto = "Crypto";
	private final String kGetPublicKeyResponse = "GetPublicKeyResponse";

	private final String kBits = "Bits";
	private final String kExponent = "Exponent";
	private final String kModulus = "Modulus";

	// private attribute
	private Integer mBits;
	private String mExponent;
	private String mModulus;

	// Default Constructor, set family and message type for every messages.
	public CGetPublicKeyResponse() throws Exception {
		setFamily(kCrypto);
		setMessageType(kGetPublicKeyResponse);
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
		xmlResponse.append("<Key>" + CRLF);
		xmlResponse.append("<" + kBits + ">" + mBits + "</" + kBits + ">"
				+ CRLF);
		xmlResponse.append("<" + kExponent + ">" + mExponent + "</" + kExponent
				+ ">" + CRLF);
		xmlResponse.append("<" + kModulus + ">" + mModulus + "</" + kModulus
				+ ">" + CRLF);
		xmlResponse.append("</Key>" + CRLF);
		return xmlResponse.toString();
	}

	/**
	 * Return Modulus
	 */
	public String getModulus() {
		return mModulus;
	}

	/**
	 * set modulus
	 * 
	 * @param modulus
	 */
	public void setModulus(String modulus) {
		mModulus = modulus;
	}

	/**
	 * Return Bits
	 */
	public Integer getBits() {
		return mBits;
	}

	/**
	 * set bits
	 * 
	 * @param bits
	 */
	public void setBits(Integer bits) {
		mBits = bits;
	}

	/**
	 * Return Exponents
	 */
	public String getExponent() {
		return mExponent;
	}

	/**
	 * set exponent
	 * 
	 * @param exponent
	 */
	public void setExponent(String exp) {
		mExponent = exp;
	}
}
