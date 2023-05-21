package com.skills4testing.core.message;

import java.util.Vector;

public class CMessageRegister {

	/**
	 * Before handling any message to corresponding action handler, message
	 * manager checks the type of the message it handles through this object and
	 * CEventDescriptor.
	 */

	// Name of the message handler.
	public String messageHandler;
	// Vector containing Message family and message type i.e. it contain the
	// objects of CMessageDescriptor.
	public Vector<?> messages;

	// Default constructor.
	public String myClassName;

	public CMessageRegister() {
	}

	/**
	 * @param messageHandler
	 *            Name of the dynamically loaded message class.
	 * @param messages
	 *            vector of CMessageDescriptor object.
	 * @param myClassName
	 *            reference to the dynamically loaded class.
	 */
	public CMessageRegister(String messageHandler, Vector<?> messages,
			String myClassName) {
		this.messages = messages;
		this.myClassName = myClassName;
		this.messageHandler = messageHandler;
	}

	/**
	 * @param messageHandler
	 *            Name of the dynamically loaded message class.
	 */
	public void setMessageHandler(String messageHandler) {
		this.messageHandler = messageHandler;
	}

	public String getMessageHandler() {
		return messageHandler;
	}

	/**
	 * @param messages
	 *            vector of CMessageDescriptor object.
	 */
	public void setMessages(Vector<?> msg) {
		this.messages = msg;
	}

	/**
	 * to get Message
	 */
	public Vector<?> getMessages() {
		return messages;
	}

	/**
	 * @param myClassName
	 *            reference to the dynamically loaded class.
	 */
	public void setClassName(String className) {
		this.myClassName = className;
	}

	/**
	 * Reference of Class
	 */

	public String getClassName() {
		return myClassName;
	}
}
