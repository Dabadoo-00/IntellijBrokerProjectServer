package com.skills4testing.core.util;

public  class CError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * List of all known errors. Each error should be in this list, and each
	 * error should only be created/thrown from 1 location in your code. If you
	 * need the same or a similar error somewhere else in your code, you need to
	 * add a new error to the list.
	 * 
	 * The list should be kept in numerically sorted order, so we can easily
	 * look up errors, and avoid to use error numbers twice.
	 * 
	 * Please check this source file in *immediately* after using it.
	 * 
	 * Error number Description Source file & function
	 */

	static final String[][] mErrorList = {
			// CError errors
			{ "100", "Error with id not found", "CError.CError" },
			{ "101", "Error with fault id not found",
					"CFaultResponse.CFaultResponse" },
			{ "102", "Session Record is not updated",
					"CValidateSession.validSession" },

			// CMessageManager errors
			{ "200", "Thread failed", "CMessageManager.handleMessage" },
			{ "201", "g_app == null", "CMessageManager.Constructor" },
			{ "202", "preferences == null", "CMessageManager.Constructor" },
			{ "203", "messagepath == null", "CMessageManager.Constructor" },
			{ "204", "Message Class Load path empty",
					"CMessageManager.loadAllHandlers" },
			{ "205", "Action Class Load path empty",
					"CMessageManager.loadAllHandlers" },
			{ "206", "Parameter xmlString cannot be null",
					"CMessageManager.messageFromXMLString" },
			{ "207", "Parameter xmlString cannot be empty",
					"CMessageManager.messageFromXMLString" },

			// CConnectionHandler errors
			{ "300", "Failed to create session", "CConnectionHandler.login" },
			{ "301", "Failed to create ping Response",
					"CConnectionHandler.ping" },
			{ "302", "Problem in Logout ", "CConnectionHandler.logout" },
			{ "303", "Problem in Login ", "CConnectionHandler.login" },
			{ "304", "Problem in handling the messages ",
					"CConnectionHandler.handleMessage" },
			{ "305", "Problem in Connecting Database dynamically ",
					"CDynamicDBConnector.getConnection" },

			

	};
	private int mID; // ID number of this error
	private String mDescription; // Description of this error
	private String mSourcePosition; // Position in source code of this error
									// (file.function)

	/**
	 * throwError(int id)
	 * 
	 * This public static call throws an error from anywhere in your code.
	 * 
	 * For example: CError.throwError(errorNumber);
	 */

	public static void throwError(int id_i) throws Exception {
		String id_s = new Integer(id_i).toString();
		String[] err_s;
		for (int x = 0; x < mErrorList.length; x++) {
			err_s = mErrorList[x];
			if (err_s[0].equals(id_s)) {
				int id = Integer.parseInt(err_s[0]);
				String description = err_s[1];
				throw new CError(id + " " + description, x);
			}
		}

		// If we get here, the error is not in mErrorList (which is a
		// programming error)
		if (id_i != 100)
			throwError(100);
		else {
			/*
			 * This should _absolutely_ never occur. It means that the error 100
			 * was removed from mErrorList.
			 * 
			 * { "100", "Error with id not found", "CError.CError"} should
			 * always be in the list
			 */
			throw new CError(
					"Impossible major malfunction in the bowels of the server. You'd better go home. Burp.");
		}
	}

	/**
	 * Constructor
	 * 
	 * Should not be called from outside CError
	 */

	protected CError(String s) {
		super(s);
	}

	/**
	 * Constructor
	 * 
	 * Should not be called from outside CError
	 */

	protected CError(String s, int index) {
		super(s);

		String[] err_s;
		err_s = mErrorList[index];

		mID = Integer.parseInt(err_s[0]);
		mDescription = err_s[1];
		mSourcePosition = err_s[2];
	}

	/**
	 * getID
	 * 
	 * Returns the id number of this error
	 */

	public int getID() {
		return mID;
	}

	/**
	 * getDescription
	 * 
	 * Returns the description of this error
	 */

	public String getDescription() {
		return mDescription;
	}

	/**
	 * getSourcePosition
	 * 
	 * Returns the position in the source code where this error occurs The
	 * format of the string is "classname.functionname"
	 */

	public String getSourcePosition() {
		return mSourcePosition;
	}

}
