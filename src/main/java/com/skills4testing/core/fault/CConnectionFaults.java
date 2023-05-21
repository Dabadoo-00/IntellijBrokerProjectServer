package com.skills4testing.core.fault;

public final class CConnectionFaults extends CFault {
	/**
	 * Creates a new CConnectionFaults object.
	 * 
	 * @param family
	 *            DOCUMENT ME!
	 * @param message
	 *            DOCUMENT ME!
	 * 
	 * @throws Exception
	 *             DOCUMENT ME!
	 */
	public CConnectionFaults(String family, String message) throws Exception {
		super(family, message);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public void commonConnectionFault(String detailsError, String solution) {
		setFaultErrors(1000, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public void inactiveUserFault(String detailsError, String solution) {
		setFaultErrors(1001, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public void invalidLoginFault(String detailsError, String solution) {
		setFaultErrors(1002, detailsError, solution);
	}
}
