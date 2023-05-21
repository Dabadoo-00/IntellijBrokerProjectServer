package com.skills4testing.core.message;

import java.util.Vector;

public interface IActionHandler {

	/* The vector will contain CActionDescriptor type objects */
	public Vector<CActionDescriptor> registerActions();

	/**
	 * All the actions which are registered by this class, must be implemented
	 * here.
	 * 
	 * @param action
	 *            event object is passed here
	 **/
	public CMessage handleMessage(CMessage msg) throws Exception;
	
	
	
}
