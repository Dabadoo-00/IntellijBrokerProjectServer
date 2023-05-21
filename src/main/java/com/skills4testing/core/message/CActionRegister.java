package com.skills4testing.core.message;

import java.util.Vector;

/**
 * This class registers all actions.
 */
public class CActionRegister {

	// Name of the action handler.
	public String actionHandler;

	// Vector containing action family and action ID i.e. it contain the objects
	// of CActionDescriptor.
	public Vector<?> actions;

	// Class name of the class to load
	public String myClassName;

	// Default constructor.
	public CActionRegister() {
	}

	/**
	 * @param actionHandler
	 *            Name of the dynamically loaded action class.
	 * @param actions
	 *            vector of CMessageDescriptor object.
	 * @param myActionObject
	 *            reference to the dynamically loaded class.
	 */
	public CActionRegister(String actionHandler, Vector<?> actions,
			String myClassName) {
		this.actionHandler = actionHandler;
		this.myClassName = myClassName;
		this.actions = actions;
	}

	/**
	 * @param actionHandler
	 *            Name of the dynamically loaded action class.
	 */
	public void setActionHandler(String actionHandler) {
		this.actionHandler = actionHandler;
	}

	/**
	 * Returns name of the action handler
	 */
	public String getActionHandler() {
		return actionHandler;
	}

	/**
	 * @param actions
	 *            vector of CActionDescriptor object.
	 */
	public void setActions(Vector<?> actionVector) {
		this.actions = actionVector;
	}

	/**
	 * Retuens Vector of CActionDescriptor
	 */
	public Vector<?> getActions() {
		return actions;
	}

	/**
	 * @param className
	 *            of the dynamically loaded class.
	 */
	public void setClassName(String className) {
		this.myClassName = className;
	}

	/**
	 * Returns the name of the class
	 */
	public String getClassName() {
		return myClassName;
	}
}
