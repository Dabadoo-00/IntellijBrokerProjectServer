package com.skills4testing.core.message;

public class CActionDescriptor {

	/* family name of the message handler [Action] */
	public String mFamily;

	/* ID of the message handler */
	public String messageType;

	/* Default constructor */
	public CActionDescriptor() {
	}

	/**
	 * @param mFamily
	 *            action family identification
	 * @param messageID
	 *            action type
	 */
	public CActionDescriptor(String mFamily, String messageType) {
		this.mFamily = mFamily;
		this.messageType = messageType;
	}

	/* set action family */
	public void setActionFamily(String mFamily) {
		this.mFamily = mFamily;
	}

	/* get action family */
	public String getMessageFamily() {
		return mFamily;
	}

	/* set action ID */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/* get action ID */
	public String getMessageType() {
		return messageType;
	}
}
