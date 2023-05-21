package com.skills4testing.core.message;

/**
 * This class contains necessary information of the message handling in each
 * class. Before sending any message to corresponding message handler, message
 * manager checks the type of the message it handles through CMessageRegister
 * and this object.
 */

public class CMessageDescriptor {

	/* family name of the message */
	public String mFamily;

	/* ID of the message */
	public String messageType;

	/* Default constructor */
	public CMessageDescriptor() {
	}

	/**
	 * @param mFamily
	 *            Message family identification.
	 * @param messageID
	 *            message name.
	 */
	public CMessageDescriptor(String mFamily, String messageType) {
		this.mFamily = mFamily;
		this.messageType = messageType;
	}

	/* set message family */
	public void setMessageFamily(String mFamily) {
		this.mFamily = mFamily;
	}

	/* get message family */
	public String getMessageFamily() {
		return mFamily;
	}

	/**
	 * set message ID
	 * 
	 * @param messageType
	 *            name of the message
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/* get message ID */
	public String getMessageType() {
		return messageType;
	}
}
