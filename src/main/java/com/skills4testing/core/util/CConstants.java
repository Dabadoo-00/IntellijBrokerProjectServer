package com.skills4testing.core.util;

/**
 * This class contains all the necessary constants.
 */

public final class CConstants {

	public CConstants() {

	}

	public static String fileSeparator = System.getProperty("file.separator");

	public static String applicationDir = System.getProperty("user.dir");

//	// True will start debug strings to be printed on the console
//	public static boolean debugOn = CPreferences.debugOn;

//	public static String kMainDatabaseName = "TestDB";

//	// Project type
//	public static int kProjectTypeAddHoc = 1;
//	public static int kProjectTypeCR = 2;

//	public static int kProjectCRPausedToCompletedDuration = 10 * 24 * 60 * 60
//			* 1000; // = 10 Days , This is the duration
//
//	// when status of automatically Parsed project
//	// will be changed to Completed.
//
//	// Strings used for constructing dynamic results table names (Dialogue_1,
//	// and Dialogue_1_Structure)
//	public static String k_Structure = "Structure";
//	public static String kDialogue_ = "Dialogue_";
//
//	public static int kMinimumPasswordLength = 4;
//	public static int kMaximumPasswordLength = 32;

	// ------------------------Log-------------------------------
	/* Path of the error log file to be stored */

	// public static final String errorLogFilePath = applicationDir +
	// fileSeparator + "Log" + fileSeparator + "ErrorLog";
	public static final String logFilePath = applicationDir + fileSeparator + "Log";
	public static final String logLinuxFilePath = fileSeparator + "var"
			+ fileSeparator + "log" + fileSeparator + "MDS";

	/* Error log file size in lines */
	public static final int ERROR_LOG_SIZE = 1;

	/* Event log file size in lines */
	public static final int EVENT_LOG_SIZE = 1;

	// ------------------------End of Log-------------------------

	// ------------------------InvitationMonitor----------------------
//	public static final int kNoOfMaximumMailToSend = 200; // Mail to send in one
															// go

	// ---------------------------------------------------------------

	// -----------------------CJDBCDirServer--------------------------
	public static final int kMAX_DATA_FETCH_SIZE = 400; // Fetch size of the
														// respondent list for
														// mail sending

	// ---------------------------------------------------------------

	// -----------------------XML Driver--------------------------

	public static final boolean kJDK14 = true;

	public static final String kXMLDriverCrimson = "org.apache.crimson.parser.XMLReaderImpl";

	public static final String kXMLDriver = "org.xml.sax.driver";

	// -------------------SMTP Related message.
//	public static final String kSmtpMessage = "Your SMTP server information(server IP, Port) may not be correct. Please check it";
//	public static final String EMAIL_TEMPLATE_FOLDER = "Stationery";

}
