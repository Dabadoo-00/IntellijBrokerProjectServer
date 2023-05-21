package com.skills4testing.exchange.login;

import java.util.Vector;

import com.skills4testing.core.log.CLogManager;
import com.skills4testing.core.message.CActionDescriptor;
import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.IActionHandler;
import com.skills4testing.core.message.MsgConst;
import com.skills4testing.core.util.CError;
import com.skills4testing.exchange.BrokerSimulator;
import com.skills4testing.exchange.login.query.CLoginQuery;
import com.skills4testing.exchange.login.query.CLogoutQuery;
import com.skills4testing.exchange.login.query.CPingQuery;


public class LoginController implements IActionHandler {

	/**
	 * 
	 * This class is responsible for Handling messages for Connection family.
	 * Messages are as below:
	 * 
	 * 1. Login. 2. Logout. 3. Ping.
	 */

//	private final String kMessageType = "LoginResponse";
//	private final int kActiveStatus = 2;
//	private String kOk = "OK";
//
//	private final int Administrator = 1;
//	private final int User = 2;
//
//	private CDateTime systemDate = new CDateTime();
	private CLogManager logManager = BrokerSimulator.g_app.getLogManager();
	boolean debugOn = BrokerSimulator.mDebugOn;
	
	
	
	// Registering the actions.
	public Vector<CActionDescriptor> registerActions() {

		Vector<CActionDescriptor> actions = new Vector<CActionDescriptor>();
		CActionDescriptor action;

		// Registering the Actions.

		action = new CActionDescriptor(MsgConst.kConnectionFamily, MsgConst.kLogin);
		actions.addElement(action);

		action = new CActionDescriptor(MsgConst.kConnectionFamily, MsgConst.kLogout);
		actions.addElement(action);

		action = new CActionDescriptor(MsgConst.kConnectionFamily, MsgConst.kPing);
		actions.addElement(action);

		return actions;
	}

	public CMessage handleMessage(CMessage message) throws Exception {

		
		
//		CDatabase mainDB = BrokerSimulator.g_app.getStorageManager().getDatabase(CConstants.kMainDatabaseName);
//		CDatabaseConnection mainDBConnection = mainDB.newConnection();
		
		CMessage responseMessage = null;
		try {
			

			if (!message.getFamily().equalsIgnoreCase(MsgConst.kConnectionFamily)) {
				CError.throwError(3200);
			}
			// Create a Login service object
			LoginService loginService = new LoginService();
			
			if (message.getMessageType().equalsIgnoreCase(MsgConst.kLogin)) {
				
				responseMessage = loginService.handleLogin((CLoginQuery) message);
				
			} else if (message.getMessageType().equalsIgnoreCase(MsgConst.kLogout)) {
				
				responseMessage = loginService.handleLogout((CLogoutQuery) message);
				
			} else if (message.getMessageType().equalsIgnoreCase(MsgConst.kPing)) {
				
				responseMessage = loginService.handlePing((CPingQuery) message);
				
			} else {
				CError.throwError(3201);
			}
		} catch (Exception e) {
			logManager.addErrorLog("LoginController.handleMessage() :: " + e.toString());
			if (debugOn) e.printStackTrace();
		} finally {
			//mainDB.releaseConnection(mainDBConnection);
		}
		return responseMessage;
	}

	
}
