package com.skills4testing.exchange.order;

import java.util.Vector;

import com.skills4testing.core.log.CLogManager;
import com.skills4testing.core.message.CActionDescriptor;
import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.IActionHandler;
import com.skills4testing.core.message.MsgConst;
import com.skills4testing.core.util.CError;
import com.skills4testing.exchange.BrokerSimulator;

import com.skills4testing.exchange.order.query.COrderExecuteQuery;


public class ExecuteOrderController  implements IActionHandler {

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

		action = new CActionDescriptor(MsgConst.kExecuteOrderFamily, MsgConst.kExecute);
		actions.addElement(action);

//		action = new CActionDescriptor(MsgConst.kExecuteOrderFamily, MsgConst.kLogout);
//		actions.addElement(action);
//
//		action = new CActionDescriptor(MsgConst.kExecuteOrderFamily, MsgConst.kPing);
//		actions.addElement(action);

		return actions;
	}

	public CMessage handleMessage(CMessage message) throws Exception {

		
		
//		CDatabase mainDB = BrokerSimulator.g_app.getStorageManager().getDatabase(CConstants.kMainDatabaseName);
//		CDatabaseConnection mainDBConnection = mainDB.newConnection();
		
		CMessage responseMessage = null;
		try {
			

			if (!message.getFamily().equalsIgnoreCase(MsgConst.kExecuteOrderFamily)) {
				CError.throwError(3200);
			}
			// Create an Order Execution service object
			OrderExecutionService orderExecutionService = new OrderExecutionService();
			
			if (message.getMessageType().equalsIgnoreCase(MsgConst.kExecute)) {
				
				responseMessage = orderExecutionService.handleOrderExecution((COrderExecuteQuery) message);
				
			} else {
				CError.throwError(3201);
			}
		} catch (Exception e) {
			logManager.addErrorLog("ExecuteOrderController.handleMessage() :: " + e.toString());
			if (debugOn) e.printStackTrace();
		} finally {
			//mainDB.releaseConnection(mainDBConnection);
		}
		return responseMessage;
	}

	
}
