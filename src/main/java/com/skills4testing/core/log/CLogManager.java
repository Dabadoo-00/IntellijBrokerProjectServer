package com.skills4testing.core.log;

import java.io.*;

import com.skills4testing.core.util.CConstants;



/**
 * This class maintains log. There are two types of log entry, error log and
 * event log. This manager initializes and creates the directory to store the
 * log files.
 */
public class CLogManager {

	CErrorLog errorLog;
	CEventLog eventLog;
	public String mLogDir;

	/**
	 * Constructor
	 */

	public CLogManager() {
		init();
	}

	/*
	 * The destination directory of the event and error log are created if no
	 * directory exists
	 */
	private void init() {
		mLogDir = CConstants.logFilePath;
		if (System.getProperty("os.name").startsWith("Linux")) {
			mLogDir = CConstants.logLinuxFilePath;

		}
		createDir(mLogDir);
		errorLog = new CErrorLog();
		eventLog = new CEventLog();
	}

	/**
	 * This method adds error log entry.
	 */
	public void addErrorLog(String log) {
		errorLog.addLog(log);
	}

	/**
	 * This method adds error log entry.
	 */
	public void addErrorLog(String log, boolean printInConsole) {
		errorLog.addLog(log, printInConsole);
	}

	/**
	 * This method adds error log entry.
	 */
	public void addEventLog(String log) {
		eventLog.addLog(log);
	}

	/**
	 * This method adds error log entry.
	 */
	public String getLogDirPath() {
		return mLogDir;
	}

	/* Creates directory */
	private void createDir(String logDir) {
		File logPath = new File(logDir);
		if (!logPath.isDirectory()) {
			logPath.mkdirs();

		}
	}
}
