package com.skills4testing.core.fault;

import com.skills4testing.core.message.CMessage;



public class CFault extends CMessage {
	private static final String FAULT_ID = "FaultID";
	private static final String DESCRIPTION = "Description";
	private static final String POSSIBLE_SOLUTION = "PossibleSolution";
	private static final String DETAILS_ERROR = "DetailsError";
	//private String mFamilyName;
	//private String mMessageName;
	private String mFaultID = "0"; // ID number of this fault
	private String mDescription = "Unknown error"; // Description of this error
	private String mPossibleSolutions = "Please contact Metatude support team.";
	private String mDetailsError = "";
	private String mFault;

	/**
	 * Creates a new CFaultT object. This constructor must be called using
	 * subclass of CFault constructor
	 */
	protected CFault(String familyName, String messageName) throws Exception {
		setFamily(familyName);
		setMessageType(messageName);
		setIsFault(true);
	}

	/**
	 * This returns the XML String. This makes specific XML fault String for
	 * invalid query. Should not be overriden by its subclass.
	 */
	public String toXML() throws Exception {
		StringBuffer xmlResponse = new StringBuffer(super.toXML());

		xmlResponse.append("<" + FAULT_ID + ">" + mFaultID + "</" + FAULT_ID
				+ ">" + CRLF);
		xmlResponse.append("<" + DESCRIPTION + ">"
				+ super.xmlEncode(mDescription) + "</" + DESCRIPTION + ">"
				+ CRLF);
		xmlResponse.append("<" + POSSIBLE_SOLUTION + ">"
				+ super.xmlEncode(mPossibleSolutions) + "</"
				+ POSSIBLE_SOLUTION + ">" + CRLF);
		xmlResponse.append("<" + DETAILS_ERROR + ">"
				+ super.xmlEncode(mDetailsError) + "</" + DETAILS_ERROR + ">"
				+ CRLF);

		return xmlResponse.toString();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getFaultID() {
		return mFaultID;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param id
	 *            DOCUMENT ME!
	 */
	public void setFaultID(String id) {
		mFaultID = id;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	protected final String getDescription() {
		return mDescription;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param description
	 *            DOCUMENT ME!
	 */
	protected final void setDescription(String description) {
		mDescription = description;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getPossibleSolution() {
		return mPossibleSolutions;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param solution
	 *            DOCUMENT ME!
	 */
	private void setPossibleSolution(String solution) {
		mPossibleSolutions = solution;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getDetailsError() {
		return mDetailsError;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param error
	 *            DOCUMENT ME!
	 */
	private void setDetailsError(String error) {
		mDetailsError = error;
	}

	/**
	 * DOCUMENT ME! This class can only be called by subclass of CFault
	 * 
	 * @param errorID
	 *            DOCUMENT ME!
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solutions
	 *            DOCUMENT ME!
	 */
	protected final void setFaultErrors(int errorID, String detailsError,
			String solutions) {
		String[] singleRowError;
		String index = new Integer(errorID).toString();
		boolean isErrorExist = false;
		for (int x = 0; x < CFaultConstant.FAULT_LIST.length; x++) {
			singleRowError = CFaultConstant.FAULT_LIST[x];

			if (singleRowError[0].equals(index)) {
				isErrorExist = true;
				setFaultID(singleRowError[0]);
				setDescription(singleRowError[1]);

				if (solutions != null) {
					setPossibleSolution(solutions);
				} else {
					setPossibleSolution(singleRowError[2]);
				}

				if (detailsError != null) {
					setDetailsError(detailsError);
				} else {
					setDetailsError(singleRowError[3]);
				}
				break;
			}
		}
		if (!isErrorExist) { // Due to programming error, error ID may not exist
								// in the error list.
			setFaultID("1");
			setDescription("Unknown error");
			setPossibleSolution("Please contact Metatude support team.");
			setDetailsError("");
		}
	}

	// ///////// Code for Common faults Methods
	// //////////////////////////////////
	// Common Fault codes 1400 - 1599

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void commonBaseFault(String detailsError, String solution) {
		setFaultErrors(1400, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invalidSessionFault(String detailsError, String solution) {
		setFaultErrors(1401, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void directoryExistsFault(String detailsError, String solution) {
		setFaultErrors(1402, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void directoryNotEmptyFault(String detailsError,
			String solution) {
		setFaultErrors(1403, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void driverNotFoundFault(String detailsError, String solution) {
		setFaultErrors(1404, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void incompatibleMDDVersionFault(String detailsError,
			String solution) {
		setFaultErrors(1405, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void incompatibleMPMVersionFault(String detailsError,
			String solution) {
		setFaultErrors(1406, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invalidDirectoryNameFault(String detailsError,
			String solution) {
		setFaultErrors(1407, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invalidPasswordFault(String detailsError, String solution) {
		setFaultErrors(1408, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invalidProjectStatusConversionFault(String detailsError,
			String solution) {
		setFaultErrors(1409, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invalidQueryFault(String detailsError, String solution) {
		setFaultErrors(1410, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void invitationIntervalNoExcededFault(String detailsError,
			String solution) {
		setFaultErrors(1411, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void missingValueFault(String detailsError, String solution) {
		setFaultErrors(1412, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void moreThanDBStringFieldSizeFault(String detailsError,
			String solution) {
		setFaultErrors(1413, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void noAdministrationRightsFault(String detailsError,
			String solution) {
		setFaultErrors(1414, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void projectCompletedFault(String detailsError, String solution) {
		setFaultErrors(1415, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unableToConnectNewDirectoryServerFault(
			String detailsError, String solution) {
		setFaultErrors(1416, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownDialogueFault(String detailsError, String solution) {
		setFaultErrors(1417, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownDirectoryFault(String detailsError, String solution) {
		setFaultErrors(1418, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownDirServerFault(String detailsError, String solution) {
		setFaultErrors(1419, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownInvitationFault(String detailsError,
			String solution) {
		setFaultErrors(1420, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownMDSFault(String detailsError, String solution) {
		setFaultErrors(1421, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownProjectFault(String detailsError, String solution) {
		setFaultErrors(1422, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownRightsFault(String detailsError, String solution) {
		setFaultErrors(1423, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void unknownServerFault(String detailsError, String solution) {
		setFaultErrors(1424, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void noDirectoryServerFault(String detailsError,
			String solution) {
		setFaultErrors(1425, detailsError, solution);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param detailsError
	 *            DOCUMENT ME!
	 * @param solution
	 *            DOCUMENT ME!
	 */
	public final void noEDSOption(String detailsError, String solution) {
		setFaultErrors(1426, detailsError, solution);
	}

	public String getFault() {
		return mFault;
	}

	public void setFault(String faultSring) {
		mFault = faultSring;
	}
}
