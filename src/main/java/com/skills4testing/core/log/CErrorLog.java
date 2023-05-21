package com.skills4testing.core.log;

import java.io.*;
import java.util.*;

import com.skills4testing.core.util.CConstants;
import com.skills4testing.core.util.Util;



/**
 * This class maintains error log file and saves it in a file. Some parameters
 * can be set in CConstants file like size of the error log buffer, the path of
 * the directory where the error log files will be saved.
 * <p>
 * 
 * Usage : <b> CLogManager clm = CLogManager();<br>
 * clm.setErrorLog(log); // log is the string to be saved <b>
 * 
 */

public class CErrorLog {

	private static Vector<CLogRecord> log = new Vector<CLogRecord>();

	private boolean mDebugOn = true; //com.wictce.server.CPreferences.debugOn;
	
	private boolean errorOn = true;

	/**
	 * Constructor
	 */
	public CErrorLog() {
	}

	/**
	 * Any error log will be appended in a Vector. LOG_SIZE will determine the
	 * size of the vector before writing it to an external file.
	 * 
	 * @param logString
	 *            An error string to be stored as log entry
	 */
	public void addLog(String logString) {
		addLog(logString, false);
	}

	/**
	 * This will take a boolean value as its parameter which if true, will print
	 * the error string at the console. At that case, an extra line for printing
	 * in concole is not needed.
	 * 
	 * @param logString
	 * @param printInScreen
	 */
	public void addLog(String logString, boolean printInScreen) {
		try {
			CLogRecord logRecord = new CLogRecord(logString);
			if (log.size() <= CConstants.ERROR_LOG_SIZE) {
				log.addElement(logRecord);
				saveLog(); // save in file // temp
				if (printInScreen) Util.toSystemMessage(logString, printInScreen);
				if (errorOn) Util.toSystemMessage("###>" + logString, printInScreen);
				refreshLog(); // refresh the Vector // temp
			}
		} catch (Exception e) {
			System.out.println("Error in addLog of CErrorLog.");
			if (mDebugOn)
				e.printStackTrace();
		}
	}

	/* This method saves the log entry to a file */
	private void saveLog() throws Exception {
		String fileName = getFileName();
		// if (CConstants.debugOn){
		// System.out.println("File name of Error Log : " + fileName);
		// }

		FileWriter fWriter = new FileWriter(fileName, true);

		for (int count = 0; count < log.size(); count++) {
			CLogRecord logRecord = new CLogRecord();
			logRecord = (CLogRecord) log.get(count);
			fWriter.write(logRecord.toString());
			if (mDebugOn) {
				System.out.println(logRecord.toString());
			}
		}
		fWriter.close();
	}

	/* It deletes all the elements form the vector */
	private void refreshLog() {
		// only one instance of log vector should be created
		// if (CConstants.debugOn){
		// System.out.println("Refreshing Error log...");
		// }
		if (log.isEmpty()) {
			log = new Vector<CLogRecord>();
		} else {
			log.removeAllElements();
		}
	}

	/* Generates unique file name */
	private String getFileName() throws Exception {
		CLogManager logMan = new CLogManager();
		return logMan.getLogDirPath() + CConstants.fileSeparator + "error.txt";
	}
}
