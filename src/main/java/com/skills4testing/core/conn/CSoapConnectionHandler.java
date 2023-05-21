package com.skills4testing.core.conn;

import java.net.*;
import java.sql.*;
import java.util.*;

import com.skills4testing.core.fault.CConnectionFaults;
import com.skills4testing.core.log.CLogManager;
import com.skills4testing.core.message.*;
import com.skills4testing.core.util.CError;
import com.skills4testing.core.util.Util;
import com.skills4testing.exchange.BrokerSimulator;

//import java.io.PrintWriter;

/**
 * The CSoapConnectionHandler class handles an incoming connection from a socket
 * The class is runnable, and its run method handles the socket from reading the
 * query until returning the response and closing it.
 */

public class CSoapConnectionHandler implements Runnable {

	protected static int activeThreadInstanceNumber = 0;

	protected static Timestamp firstThreadEntryTime = null;

	private Socket mSocket = null;
		
	
	// global debug on
	
	private  static boolean mDebugOn = BrokerSimulator.mDebugOn;
	
	private CLogManager mLogManager = BrokerSimulator.g_app.getLogManager();
			
	//private boolean mPrintBlock = BrokerSimulator.g_app.mPrintBlock;
		
	private Enumeration<CMessageRegister> mMessageEnum = BrokerSimulator.g_app.getMessageManager().getMessagesVector().elements();
	
	private Enumeration<CActionRegister> mActionEnumT = BrokerSimulator.g_app.getMessageManager().getActionHandlersVector().elements();

	/**
	 * Constructor
	 */

	public CSoapConnectionHandler() {	}

	public CSoapConnectionHandler(Socket socket) {
		this.mSocket = socket;
		try {

			//mSocket.setTcpNoDelay(false);
			//mSocket.setKeepAlive(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setClientSocket(Socket socket){
		this.mSocket = socket;
	}

	/**
	 * run
	 * 
	 * Run handles the connection with the client, it will retrieve the xml
	 * query string from the socket connection, handle the message, return the
	 * response string to the socket, and close the socket connection.
	 */

	public void run() {

		setThreadEntry();

		
		//boolean debugOn = g_app.getPreferences().debugOn;

		String xmlQueryString = null;
		String xmlResponseString = null;
		//String xmlFaultString = null;
		boolean isFault = false;

		CHttpHandler httpHandler = new CHttpHandler();

		try {
			// 1. Get the query XML string from the socket

			//PrintWriter output = new PrintWriter(mSocket.getOutputStream(),true);
			// mSocket.wait(100);
			xmlQueryString = httpHandler.handleRequest(mSocket.getInputStream());

			System.out.println("Message from client: " + xmlQueryString);

			// 2. Create a query message object from the string

			CMessage query = null;
			if ((query = (CMessage) messageFromXMLString(xmlQueryString)) == null) {
				CError.throwError(704);
				mLogManager.addEventLog("CSoapConnectionHandler.run() Error (1) CError(704) ");

			}

			// Validate session

			// 3. Handle the message and receive a response message
			CMessage response = null;
			if ((response = (CMessage) handleMessage(query)) == null) {
				mLogManager.addEventLog("CSoapConnectionHandler.run() Error (2) CError(705) ");
				query = null;
				CError.throwError(705);
			}

			// 4. Send the response message through the socket
			// if (response instanceof CFaultT)
			// if (response.getIsFault()) {
			// CFaultT fault = (CFaultT)response;
			// isFault = true;
			// }
			isFault = response.getIsFault();
			xmlResponseString = response.toXML();
			String soapString = xmlToSoapWrapper(xmlResponseString, isFault);

			System.out.println("Message To client: " + soapString);
			// httpHandler.handleResponse( output, soapString);
			httpHandler.handleResponse(mSocket.getOutputStream(), soapString);

			query = null;
			response = null;
			// 5. Close the socket (see Finally block)

		} catch (Exception ioe) {

			if (mDebugOn) {
				System.out.println("Exception A in CSoapConnection.run");
				ioe.printStackTrace();
			}
			mLogManager.addErrorLog("CSoapConnectionHandler.run() Error :: " + ioe.toString());

			try { // Modified
				// CUnknownMDSFault unknownServerFault = new
				// CUnknownMDSFault("Unknown","Unknown");
				// xmlFaultString = unknownServerFault.getFault();

				// CFaultT unknownServerFault = new CFaultT ("Unknown",
				// "Unknown", true);
				CConnectionFaults unknownServerFault = new CConnectionFaults("Unknown", "Unknown");
				String faultSoapString = xmlToSoapWrapper(unknownServerFault.toXML(), true);
				httpHandler.handleResponse(mSocket.getOutputStream(),faultSoapString);
			} catch (Exception ex) {
				if (mDebugOn) {
					System.out.println("Exception B in CSoapConnection.run");
					ex.printStackTrace();
				}
				mLogManager.addErrorLog("CSoapConnectionHandler.run() Error (2) :: "+ ex.toString());
			}
		} finally {
			setThreadExit();
			try {
				if (mSocket != null)
					mSocket.close();
			} catch (Exception e) {
				if (mDebugOn) {
					System.out.println("Exception C in CSoapConnection.run");
					e.printStackTrace();
				}
				mLogManager.addErrorLog("CSoapConnectionHandler.run() Error (3) :: "+ e.toString());
			}
		}
	}
	
	// action handler : handles actions
	public CMessage handleMessage(CMessage message) throws Exception {
		CMessage returnActionMessage = null;
		String familyName = message.getFamily();
		String id = message.getMessageType();

		CActionRegister actionReg;

		

		// Checks for the specific action handler
		while (mActionEnumT.hasMoreElements()) {
			actionReg = (CActionRegister) mActionEnumT.nextElement();
			Enumeration<?> enumInner = actionReg.actions.elements();

			while (enumInner.hasMoreElements()) {
				CActionDescriptor actionDesc = (CActionDescriptor) enumInner
						.nextElement();

				if (actionDesc.mFamily.toString().trim()
						.equalsIgnoreCase(familyName.trim())) {
					if (actionDesc.messageType.toString().trim()
							.equalsIgnoreCase(id.trim())) {

						String className = actionReg.myClassName;
						IActionHandler myClass = (IActionHandler) Class
								.forName(className).newInstance();
						returnActionMessage = (CMessage) myClass
								.handleMessage(message);

						/*
						 * CHandlerThread handlerThread = new
						 * CHandlerThread(actionReg , message);
						 * 
						 * handlerThread.start();
						 * 
						 * // how to check whether thread is done while
						 * (handlerThread.isAlive() |
						 * (handlerThread.getThreadStatus() ==
						 * CHandlerThread.kThreadRunning)){ Thread.yield(); }
						 * 
						 * int threadStatus = handlerThread.getThreadStatus();
						 * if (threadStatus != CHandlerThread.kThreadSucceeded)
						 * { CError.throwError(200); }
						 * 
						 * returnActionMessage = handlerThread.getMessage();
						 * 
						 * handlerThread = null; 
						 */

						// System.out.println("CSoapConnectionHandler >>>");
						// Thread.currentThread().getThreadGroup().list();

						return returnActionMessage;
					}
				}
			}
		}
		return returnActionMessage;
	}


	/**
	 * setThreadEntry
	 * 
	 * This method will increment the activeThreadInstanceNumber variable by one
	 * and if this is the first thread that is if the activeThreadInstanceNumber
	 * = 1 after increment, then it will also keet the current timestamp in the
	 * variable firstThreadEntryTime.
	 */
	private void setThreadEntry() {
		activeThreadInstanceNumber++;
		// System.out.println("setThreadEntry :: Active Thread now # " +
		// activeThreadInstanceNumber);
		if (activeThreadInstanceNumber == 1)
			firstThreadEntryTime = new Timestamp(System.currentTimeMillis());
		// this.showThreadCount();
	}

	/**
	 * setThreadExit
	 * 
	 * This method will decrement the activeThreadInstanceNumber variable by one
	 * and if this is the last thread that is if the activeThreadInstanceNumber
	 * = 0 after decrement, then it will also flush the timestamp from the
	 * variable firstThreadEntryTime.
	 */
	private void setThreadExit() {
		if (activeThreadInstanceNumber > 0) {
			activeThreadInstanceNumber--;
		}
		// System.out.println("setThreadExit::Active Thread now # " +
		// activeThreadInstanceNumber);
		if (activeThreadInstanceNumber == 0) {
			firstThreadEntryTime = null;
		}
		// this.showThreadCount();
	}

	/**
	 * validateConnection
	 * 
	 * If the first thread entry time is more than 5 minutes, then we will
	 * reduce the variable activeThreadInstanceNumber.
	 */
	public static void validateConnection() {
		if (mDebugOn)Util.toSystemMessage("In validate : Soap Connection", mDebugOn);
		if (activeThreadInstanceNumber > 0) {
			long timeDiff = System.currentTimeMillis()
					- firstThreadEntryTime.getTime();
			if (timeDiff > 5 * 60 * 1000) {
				activeThreadInstanceNumber--;
				if (activeThreadInstanceNumber == 0) {
					firstThreadEntryTime = null;
				}
			}
		}
		if (mDebugOn)
			showThreadCount();
	}

	/**
	 * getThreadCount()
	 */
	public static int getThreadCount() {
		return activeThreadInstanceNumber;
	}

	/**
	 * showThreadCount()
	 * 
	 */
	public static void showThreadCount() {
		Util.toSystemMessage("Active client connection # "
				+ activeThreadInstanceNumber, mDebugOn);
		if (firstThreadEntryTime != null) Util.toSystemMessage("First connection entry time # "
							+ firstThreadEntryTime, mDebugOn);
	}

	/**
	 * xmlToSoapWrapper
	 * 
	 * This utility function takes an XML message string, and an optional
	 * faultString.
	 * 
	 * It wraps a soap message around the xml, and returns the soap message as a
	 * string.
	 */

	private String xmlToSoapWrapper(String xmlMessage, boolean fault) {

		String CRLF = "\r\n";
		StringBuffer buf = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
		// buf.append(CRLF);
		buf.append("<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\" SOAP:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		// buf.append(CRLF);
		buf.append("<SOAP:Body>");
		// buf.append(CRLF);

		if (fault) {
			buf.append("<SOAP:Fault>");
			buf.append(CRLF);
			buf.append("<faultcode>SOAP:Server</faultcode>");
			buf.append(CRLF);
			// buf.append("<faultstring>"+ faultString + "</faultstring>");
			// buf.append(CRLF);
			buf.append("<detail>");
			buf.append(CRLF);
			buf.append("<mds:errordetail xmlns:mds=\"http://schemas.metatude.com/soap/error\">");
			buf.append(CRLF);
			buf.append(xmlMessage);
			buf.append("</mds:errordetail>");
			buf.append(CRLF);
			buf.append("</detail>");
			buf.append(CRLF);
			buf.append("</SOAP:Fault>");
			buf.append(CRLF);

		} else {
			buf.append(xmlMessage);
		}

		buf.append("</SOAP:Body>");
		// buf.append(CRLF);
		buf.append("</SOAP:Envelope>");
		// buf.append(CRLF);

		String soapReply = buf.toString();
		return soapReply;
	}

	/**
	 * messageFromXMLString
	 * 
	 * This call will take an XML string, and return the correct CMessage object
	 * from it.
	 */

	public CMessage messageFromXMLString(String xmlString) throws Exception {

		// 1. First check our parameter, better safe than sorry
		if (xmlString == null) {
			CError.throwError(206);
		}
		if (xmlString.length() < 1)

		{
			CError.throwError(207);
		}

		// 2. Ask CMessage to reconstruct itself from the string.
		// This message will only have the correct family and message type

		CMessage message = new CMessage();
		CMessage returnMessage = (CMessage) message.fromXML(xmlString);

		String familyName = returnMessage.getFamily();
		String id = returnMessage.getMessageType();

		CMessageRegister messageReg;
		

		// Checks for the specific event handler
		while (mMessageEnum.hasMoreElements()) {
			messageReg = (CMessageRegister) mMessageEnum.nextElement();
			Enumeration<?> enumInner = messageReg.messages.elements();

			while (enumInner.hasMoreElements()) {
				CMessageDescriptor messageDesc = (CMessageDescriptor) enumInner
						.nextElement();

				// System.out.println(messageDesc.mFamily.toString());

				if (messageDesc.mFamily.toString().trim()
						.equalsIgnoreCase(familyName.trim())) {
					if (messageDesc.messageType.toString().trim()
							.equalsIgnoreCase(id.trim())) {
						String className = messageReg.myClassName;
						CMessage myClass = (CMessage) Class.forName(className)
								.newInstance();
						return myClass.fromXML(xmlString);
					}
				}
			}
		}

		if (id.equalsIgnoreCase(MsgConst.kDeleteQuery)) {
			String packagePathForCDeleteQuery = "com.wictce.server.generalmsg.query.CDeleteQuery";
			CMessage myClass = (CMessage) Class.forName(
					packagePathForCDeleteQuery).newInstance();
			myClass.setFamily(familyName);
			myClass.setMessageType(id);
			return myClass.fromXML(xmlString);
		} else if (id.equalsIgnoreCase(MsgConst.kGetQuery)) {
			String packagePathForCGetQuery = "com.wictce.server.generalmsg.query.CGetQuery";
			CMessage myClass = (CMessage) Class
					.forName(packagePathForCGetQuery).newInstance();
			myClass.setFamily(familyName);
			myClass.setMessageType(id);

			return myClass.fromXML(xmlString);
		} else if (id.equalsIgnoreCase(MsgConst.kListQuery)) {
			String packagePathForCListCodeQuery = "com.wictce.server.generalmsg.query.CListCodeQuery";
			CMessage myClass = (CMessage) Class.forName(
					packagePathForCListCodeQuery).newInstance();
			myClass.setFamily(familyName);
			myClass.setMessageType(id);
			return myClass.fromXML(xmlString);
		}
		if (id.equalsIgnoreCase(MsgConst.kAbortQuery)) {
			String packagePathForCAbortQuery = "com.wictce.server.generalmsg.query.CAbortQuery";
			CMessage myClass = (CMessage) Class.forName(
					packagePathForCAbortQuery).newInstance();
			myClass.setFamily(familyName);
			myClass.setMessageType(id);
			return myClass.fromXML(xmlString);
		}
		if (id.equalsIgnoreCase(MsgConst.kReleaseLockQuery)) {
			String packagePathForCAbortQuery = "com.wictce.server.generalmsg.query.CReleaseLockQuery";
			CMessage myClass = (CMessage) Class.forName(
					packagePathForCAbortQuery).newInstance();
			myClass.setFamily(familyName);
			myClass.setMessageType(id);
			return myClass.fromXML(xmlString);
		}

		return null;
	}


}