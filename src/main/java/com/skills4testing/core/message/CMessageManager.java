package com.skills4testing.core.message;

import java.util.*;

import com.skills4testing.core.util.CDateTime;
import com.skills4testing.core.util.CError;


/**
 * CMessageManager
 * 
 * 
 */

public class CMessageManager {

	public boolean debugOn = true; // CPreferences.debugOn;// For debugging

	public CMessage returnActionMessage;

	private Vector<CMessageRegister> mMessages;

	private Vector<CActionRegister> mActionHandlers;

	

	// Constructor
	public CMessageManager() throws Exception {

		//loadAllHandlers();
	}

	/**
	 * getMessagesVector
	 * 
	 * return vector of messages
	 */
	public Vector<CMessageRegister> getMessagesVector() {
		return mMessages;
	}

	/**
	 * getActionHandlersVector
	 * 
	 * return action vectors
	 */
	public Vector<CActionRegister> getActionHandlersVector() {
		return mActionHandlers;
	}

	/**
	 * loadMessageClasses
	 * 
	 * This function scans a directory, and loads all class files from the
	 * directory. The class files should extend the
	 * com.metatude.mds.msg.CMessage class.
	 * 
	 * The messageClassDirectory is a File object for the directory to scan
	 */

	public void loadMessageClasses() throws Exception {
		// 1. Get a list of all files in the directory, the list contains full
		// file names
		mMessages = new Vector<CMessageRegister>();
		Vector<String> fileList = getMessageClassList();
		// 2. Register each file
		for (ListIterator<String> i = fileList.listIterator(); i.hasNext();) {
			String fullFileName = (String) i.next();
			try {
				// returns vector of java .class list
				loadMessageHandler(fullFileName);
				if (debugOn) {
					System.out.println("CMessageManager "
							+ CDateTime.toSystemOutDate() + "> " + fullFileName
							+ " Loaded.\n");
				}

			} catch (Exception exp) {
				CError.throwError(204);
			}
		}
	}

	/**
	 * loadMessageHandlers
	 * 
	 * This function scans a directory, and loads all class files from the
	 * directory. The class files should extend the
	 * com.metatude.mds.msg.CMessage class.
	 * 
	 * The messageClassDirectory is a File object for the directory to scan
	 */

	public void loadMessageHandlers() throws Exception {
		// 1. Get a list of all files in the directory, the list contains full
		// file names

		mActionHandlers = new Vector<CActionRegister>();
		Vector<String> fileList = getMessageHandlerClassList();

		// 2. Register each file
		for (ListIterator<String> i = fileList.listIterator(); i.hasNext();) {
			String fullFileName = (String) i.next();
			try {
				// returns vector of java .class list
				loadActionHandler(fullFileName);
				if (debugOn) {
					System.out.println("CMessageManager "
							+ CDateTime.toSystemOutDate() + "> " + fullFileName
							+ " Loaded.\n");
				}

			} catch (Exception exp) {
				CError.throwError(204);
			}
		}
	}

	/**
	 * Loads all the messages and its handlers from a directory.
	 */
	public void loadAllHandlers() throws Exception {
		// Here loading message parsers
		loadMessageClasses();
		loadMessageHandlers();
	}

	/**
	 * Loads specific message according to the parameter
	 */
	public void loadMessageHandler(String messageHandler) {
		CMessage myClass;
		String className;
		CMessageRegister messageReg;

		try {
			// myClass = (CMessage)Class.forName(mMessagePackageName +
			// messageHandler).newInstance();
			// className = mMessagePackageName + messageHandler;
			myClass = (CMessage) Class.forName(messageHandler).newInstance();
			className = messageHandler;
			if (myClass instanceof CMessage) {
				if (debugOn) {
					System.out.println("CMessageManager "
							+ CDateTime.toSystemOutDate() + "> Load Class : "
							+ myClass.getClass().getName());
				}

				// messageReg = new
				// CMessageRegister(messageHandler,myClass.registerMessages(),myClass);
				messageReg = new CMessageRegister(messageHandler,
						myClass.registerMessages(), className);
				mMessages.addElement(messageReg);
			}
		} catch (Exception e) {
			if (debugOn) {
				System.out.println("EXCEPTION> CMessageManager> Class "
						+ messageHandler + " could not be loaded. ");
				System.out.println("EXCEPTION STRING> " + e + "\r\nTRACE:");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Load specific action Handler according to the parameter
	 */
	public void loadActionHandler(String actionHandler) {
		IActionHandler myClass;
		String myClassName;
		CActionRegister actionReg;

		try {
			// myClass = (IActionHandler)Class.forName(mHandlerPackageName +
			// actionHandler).newInstance();
			// myClassName = mHandlerPackageName + actionHandler;
			myClass = (IActionHandler) Class.forName(actionHandler)
					.newInstance();
			myClassName = actionHandler;
			actionReg = new CActionRegister(actionHandler,
					myClass.registerActions(), myClassName);
			mActionHandlers.addElement(actionReg);
		} catch (Exception e) {
			if (debugOn) {
				e.printStackTrace();
				System.out.println("EXCEPTION> CMessageManager "
						+ CDateTime.toSystemOutDate() + "> Class "
						+ actionHandler + " could not be loaded. ");
			}
		}
	}

	/**
	 * Unloads all the messages.
	 */
	public void unloadMessageHandlers() {
		mMessages = null;
	}

	/**
	 * Unloads all the action handlers.
	 */
	public void unloadActionHandlers() {
		mActionHandlers = null;
	}

	/**
	 * Unloads a specific message class. <br>
	 * 
	 * @param messageClassName
	 *            The name of the class to unload t must be just the name.Foe
	 *            example, com.metatude.mxm.handlers.databaseHandlers sould be
	 *            just databaseHandlers
	 */
	public void unloadMessage(String messageClassName) {
		CMessageRegister messageReg;
		Enumeration<CMessageRegister> messageRegEnum = mMessages.elements();

		while (messageRegEnum.hasMoreElements()) {
			messageReg = (CMessageRegister) messageRegEnum.nextElement();

			if (messageReg.messageHandler.equalsIgnoreCase(messageClassName)) {
				if (debugOn) {
					System.out.println("CMessageManager "
							+ CDateTime.toSystemOutDate() + "> The class "
							+ messageClassName + " is unloaded successfully.");
				}
				mMessages.remove(messageReg);
			}
		}
	}

	/**
	 * Unloads a specific message handler (Action).
	 * <p>
	 * 
	 * @param messageHandler
	 *            The name of the message handler. It must be just the name.Foe
	 *            example, com.metatude.mds.msg.databaseHandlers sould be just
	 *            databaseHandlers
	 */
	public void unloadMessageHandler(String actionClassName) {
		CActionRegister actionReg;
		Enumeration<CActionRegister> actionRegEnum = mActionHandlers.elements();

		while (actionRegEnum.hasMoreElements()) {
			actionReg = (CActionRegister) actionRegEnum.nextElement();

			if (actionReg.actionHandler.equalsIgnoreCase(actionClassName)) {
				if (debugOn) {
					System.out.println("CMessageManager "
							+ CDateTime.toSystemOutDate() + "> The class "
							+ actionClassName + " is unloaded successfully.");
				}
				mActionHandlers.remove(actionReg);
			}
		}
	}

	/**
	 * messageFromXMLString
	 * 
	 * This call will take an XML string, and return the correct CMessage object
	 * from it.
	 */
	/*
	 * public CMessage messageFromXMLString(String xmlString) throws Exception {
	 * 
	 * // 1. First check our parameter, better safe than sorry if (xmlString ==
	 * null) { CError.throwError(206); } if (xmlString.length() < 1) {
	 * CError.throwError(207); }
	 * 
	 * // 2. Ask CMessage to reconstruct itself from the string. // This message
	 * will only have the correct family and message type
	 * 
	 * CMessage message = new CMessage(); CMessage returnMessage = (CMessage)
	 * message.fromXML(xmlString);
	 * 
	 * 
	 * String familyName = returnMessage.getFamily(); String id =
	 * returnMessage.getMessageType();
	 * 
	 * CMessageRegister messageReg; Enumeration enum = mMessages.elements();
	 * 
	 * // Checks for the specific event handler while(enum.hasMoreElements()){
	 * messageReg = (CMessageRegister)enum.nextElement(); Enumeration enumInner
	 * = messageReg.messages.elements();
	 * 
	 * while (enumInner.hasMoreElements()){ CMessageDescriptor messageDesc =
	 * (CMessageDescriptor)enumInner.nextElement();
	 * 
	 * if
	 * (messageDesc.mFamily.toString().trim().equalsIgnoreCase(familyName.trim
	 * ())){ if
	 * (messageDesc.messageType.toString().trim().equalsIgnoreCase(id.trim())){
	 * String className = getClassName(messageReg.messageHandler.toString());
	 * CMessage obj = (CMessage)messageReg.myObject;
	 * 
	 * return returnMessage = (CMessage)obj.fromXML(xmlString); } } } } return
	 * null; }
	 */

	/*
	 * // action handler : handles actions public CMessage
	 * handleMessage(CMessage message) throws Exception { CMessage
	 * returnActionMessage = null;
	 * 
	 * String familyName = message.getFamily(); String id =
	 * message.getMessageType();
	 * 
	 * CActionRegister actionReg;
	 * 
	 * Enumeration enum = mActionHandlers.elements();
	 * 
	 * // Checks for the specific action handler while(enum.hasMoreElements()){
	 * actionReg = (CActionRegister)enum.nextElement(); Enumeration enumInner =
	 * actionReg.actions.elements();
	 * 
	 * while (enumInner.hasMoreElements()){ CActionDescriptor actionDesc =
	 * (CActionDescriptor)enumInner.nextElement();
	 * 
	 * if
	 * (actionDesc.mFamily.toString().trim().equalsIgnoreCase(familyName.trim(
	 * ))){ if
	 * (actionDesc.messageType.toString().trim().equalsIgnoreCase(id.trim())){
	 * 
	 * CHandlerThread handlerThread = new CHandlerThread(actionReg , message);
	 * handlerThread.start(); // how to check whether thread is done while
	 * (handlerThread.isAlive() | (handlerThread.getThreadStatus() ==
	 * CHandlerThread.kThreadRunning)){ Thread.yield(); }
	 * 
	 * int threadStatus = handlerThread.getThreadStatus(); if (threadStatus !=
	 * CHandlerThread.kThreadSucceeded) { CError.throwError(200); }
	 * 
	 * returnActionMessage = handlerThread.getMessage();
	 * 
	 * handlerThread = null; // Saadat on 16-01-2002
	 * 
	 * return returnActionMessage; } } } } return returnActionMessage; }
	 */
	/*
	 * // returns class reference public Object getReplyMessage(String family,
	 * String type){ CMessage returnMessage = null; CMessage obj = null;
	 * CMessageRegister messageReg; Enumeration enum = mMessages.elements();
	 * 
	 * // Checks for the specific message while(enum.hasMoreElements()){
	 * messageReg = (CMessageRegister)enum.nextElement(); Enumeration enumInner
	 * = messageReg.messages.elements();
	 * 
	 * while (enumInner.hasMoreElements()){ CMessageDescriptor messageDesc =
	 * (CMessageDescriptor)enumInner.nextElement();
	 * 
	 * if
	 * (messageDesc.mFamily.toString().trim().equalsIgnoreCase(family.trim())){
	 * if
	 * (messageDesc.messageType.toString().trim().equalsIgnoreCase(type.trim()
	 * )){ String className =
	 * getClassName(messageReg.messageHandler.toString()); return obj =
	 * (CMessage)messageReg.myObject; } } } } return null; }
	 */

	/**
	 * To get class name with .class extension
	 */
//	private String getClassName(String className) {
//		int lastDot = className.lastIndexOf(".");
//		className = className.substring(lastDot + 1);
//		return className;
//	}

	/**
	 * getFilenameExtension
	 * 
	 * This function returns the extension of a filename. It looks for the last
	 * occurrence of a dot ("."), and returns the part after it (excluding the
	 * dot).
	 * 
	 * If there is no dot in the filename, this function returns null If there
	 * are no characters after the dot, this function returns null If the
	 * filename == null, this function returns null
	 */
//	private String getFilenameExtension(String filename) {
//		if (filename == null)
//			return null;
//		int lastDot = filename.lastIndexOf(".");
//		if (lastDot < 0)
//			return null;
//		if (lastDot == filename.length())
//			return null;
//
//		String extension = filename.substring(lastDot + 1);
//		return extension;
//	}

	/**
	 * getMessageClassList
	 * 
	 * This function returns a list of message classes to load dynamically It is
	 * a patch for R1 because we are unable to actually load classes from a
	 * directory at the moment. See loadMessageClasses for more info.
	 */

	private Vector<String> getMessageClassList() {
		Vector<String> v = new Vector<String>();

		String packagePath = "com.wictce.server.generalmsg.query.";
		v.add(packagePath + "CGetQuery");
		v.add(packagePath + "CDeleteQuery");
		v.add(packagePath + "CAbortQuery");
		v.add(packagePath + "CListCodeQuery");

		// PROJECT
		packagePath = "com.wictce.server.project.query.";

		v.add(packagePath + "CAddProjectQuery");
		v.add(packagePath + "CModifyProjectQuery");

		// FUNDER
		packagePath = "com.wictce.server.funder.query.";

		v.add(packagePath + "CAddFunderQuery");
		v.add(packagePath + "CModifyFunderQuery");

		// BENEFICIERY

		packagePath = "com.wictce.server.benefiaciary.query.";

		v.add(packagePath + "CAddBenefiaciaryQuery");
		v.add(packagePath + "CModifyBenefiaciaryQuery");
		v.add(packagePath + "CAdvancedSearchQuery");

		// FUND

		packagePath = "com.wictce.server.fund.query.";

		v.add(packagePath + "CAddFundQuery");
		v.add(packagePath + "CModifyFundQuery");

		// SERVICE

		packagePath = "com.wictce.server.service.query.";

		v.add(packagePath + "CAddServiceQuery");
		v.add(packagePath + "CModifyServiceQuery");

		// STAFF

		packagePath = "com.wictce.server.staff.query.";

		v.add(packagePath + "CAddStaffQuery");
		v.add(packagePath + "CModifyStaffQuery");
		// APPOINTMENT

		packagePath = "com.wictce.server.appointment.query.";

		v.add(packagePath + "CAddAppointmentQuery");
		v.add(packagePath + "CModifyAppointmentQuery");
		v.add(packagePath + "CGetAppointmentQuery");

		// USER

		packagePath = "com.wictce.server.user.query.";

		v.add(packagePath + "CAddUserQuery");
		v.add(packagePath + "CEditUserQuery");

		// PARTNER

		packagePath = "com.wictce.server.partner.query.";

		v.add(packagePath + "CAddPartnerQuery");
		v.add(packagePath + "CModifyPartnerQuery");

		// CONNECTION

		packagePath = "com.wictce.server.connection.query.";

		v.add(packagePath + "CLoginQuery");
		v.add(packagePath + "CLogoutQuery");

		// Category

		packagePath = "com.wictce.server.project.analysis.category.query.";

		v.add(packagePath + "CAddCategoryQuery");
		v.add(packagePath + "CGetCategoryListQuery");
		v.add(packagePath + "CModifyCategoryQuery");

		// Code

		packagePath = "com.wictce.server.project.analysis.code.query.";

		v.add(packagePath + "CAddCodeQuery");
		v.add(packagePath + "CGetCodeListQuery");
		v.add(packagePath + "CModifyCodeQuery");
		v.add(packagePath + "CDeleteDataQuery");

		// Sub-Category

		packagePath = "com.wictce.server.project.analysis.category.subcategory.query.";

		v.add(packagePath + "CAddSubCategoryQuery");
		v.add(packagePath + "CGetSubCategoryListQuery");
		v.add(packagePath + "CModifySubCategoryQuery");

		// Report
		packagePath = "com.wictce.server.project.analysis.reports.query.";
		v.add(packagePath + "CGetProjectReportQuery");
		v.add(packagePath + "CListReportSummaryQuery");

		// // REMINDER
		// packagePath = "com.metatude.mds.reminder.query.";
		//
		// v.add(packagePath + "CAddReminderQuery");
		// v.add(packagePath + "CEditReminderQuery");
		// v.add(packagePath + "CDeleteReminderQuery");
		// v.add(packagePath + "CGetReminderQuery");
		// v.add(packagePath + "CListRemindersQuery");
		//
		// // INVITATION
		// packagePath = "com.metatude.mds.invitation.query.";
		// v.add(packagePath + "CAddInvitationsQuery");
		// v.add(packagePath + "CDeleteInvitationQuery");
		// v.add(packagePath +"CGetInvitationQuery");

		return v;
	}

	/**
	 * getMessageHandlerClassList
	 * 
	 * This function returns a list of message handler classes to load
	 * dynamically It is a patch for R1 because we are unable to actually load
	 * classes from a directory at the moment. See loadMessageClasses for more
	 * info.
	 */

	private Vector<String> getMessageHandlerClassList() {
		Vector<String> v = new Vector<String>();
		// CONNECTION
		String packagePath = "com.wictce.server.connection.";
		v.add(packagePath + "CConnectionHandler");
		v.add(packagePath + "CCryptoHandler");

		// USER
		// packagePath = "com.metatude.mds.user.";
		v.add(packagePath + "CUserActionHandler");
		v.add(packagePath + "CUserDirActionHandler");

		// PROJECT
		packagePath = "com.wictce.server.project.";
		v.add(packagePath + "CProjectActionHandler");

		// FUNDERS
		packagePath = "com.wictce.server.funder.";
		v.add(packagePath + "CFunderActionHandler");

		// BENEFICIERY
		packagePath = "com.wictce.server.benefiaciary.";
		v.add(packagePath + "CBenefiaciaryActionHandler");

		// FUND
		packagePath = "com.wictce.server.fund.";
		v.add(packagePath + "CFundActionHandler");

		// SERVICE
		packagePath = "com.wictce.server.service.";
		v.add(packagePath + "CServiceActionHandler");

		// STAFF
		packagePath = "com.wictce.server.staff.";
		v.add(packagePath + "CStaffActionHandler");

		// APPOINTMENT
		packagePath = "com.wictce.server.appointment.";
		v.add(packagePath + "CAppointmentActionHandler");

		// USER
		packagePath = "com.wictce.server.user.";
		v.add(packagePath + "CUserActionHandler");

		// Partner
		packagePath = "com.wictce.server.partner.";
		v.add(packagePath + "CPartnerActionHandler");

		// Category
		packagePath = "com.wictce.server.project.analysis.category.";
		v.add(packagePath + "CCategoryActionHandler");

		// Code
		packagePath = "com.wictce.server.project.analysis.code.";
		v.add(packagePath + "CCodeActionHandler");

		// Code
		packagePath = "com.wictce.server.project.analysis.category.subcategory.";
		v.add(packagePath + "CSubCategoryActionHandler");

		// Report
		packagePath = "com.wictce.server.project.analysis.reports.";
		v.add(packagePath + "CReportActionHandler");

		return v;
	}
}// end of Class

