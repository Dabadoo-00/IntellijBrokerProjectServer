package com.skills4testing.core.log;

import java.io.*;
import java.util.*;

import com.skills4testing.core.util.CConstants;
import com.skills4testing.core.util.Util;



public class CEventLog {

	/* Contains log record of specified size */
	private static Vector<CLogRecord> log = new Vector<CLogRecord>();

	private boolean mDebugOn = true; // com.wictce.server.CPreferences.debugOn;
	private boolean mErrorOn = true;
	/**
	 * Constructor
	 */
	public CEventLog() {
	}

	/**
	 * Any event log will be appended in a Vector. EVENT_LOG_SIZE will determine
	 * the size of the vector before writing it to an external file.
	 * 
	 * @param logString
	 *            An error string to be stored as log entry
	 */
	public void addLog(String logString) {
		try {
			CLogRecord logRecord = new CLogRecord(logString);
			if (log.size() <= CConstants.ERROR_LOG_SIZE) {
				log.addElement(logRecord);
				saveLog(); // save in file
				if (mErrorOn)  Util.toSystemMessage("@@@" + logString, mErrorOn);
				refreshLog(); // refresh the Vector
			}
		} catch (Exception e) {
			System.out.println("Error in addLog of CEventLog.");
			if (mDebugOn)
				e.printStackTrace();
		}
	}

	/* This method saves the log entry to a file */
	private void saveLog() throws Exception {
		String fileName = getFileName();
		// if (CConstants.debugOn){
		// System.out.println("Event Log file name " + fileName);
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

	/* This method refreshes log vector, that means deletes all records */
	private void refreshLog() {
		// only one instance of log vector should be created
		// if (CConstants.debugOn){
		// System.out.println("Refreshing Event log...");
		// }
		if (!log.isEmpty()) {
			log.removeAllElements();
		}
		log = new Vector<CLogRecord>();
	}

	/* Returns unique file name */
	private String getFileName() throws Exception {
		CLogManager logMan = new CLogManager();
		return logMan.getLogDirPath() + CConstants.fileSeparator + "event.txt";
	}
}
