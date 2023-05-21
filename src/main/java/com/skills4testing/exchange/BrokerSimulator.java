package com.skills4testing.exchange;



import com.skills4testing.core.log.*;
import com.skills4testing.core.message.CMessageManager;
import com.skills4testing.core.soap.CConnectionManager;
import com.skills4testing.core.util.CError;

public class BrokerSimulator 
{
    
	public static BrokerSimulator g_app;


	public static boolean mPrintBlock = false;
	public static boolean mDebugOn = false;
	
	private CMessageManager mMessageManager;
	private CConnectionManager mConnectionManager;
	private CLogManager mLogManager;
	private CPreferences mPreferences;
	
//	private CStorageManager mStorageManager;
//	private Vector mListOfMDSTables = new Vector();
//	private CDatabase mMainDB;
//	private CDatabaseConnection mMainDBConnection;
//	private DatabaseMetaData mDBmetaData;
	
	
	/**
	 * Constructor
	 */
	public BrokerSimulator() throws Exception {
		g_app = this;

		// Initializes log manager
		mLogManager = new CLogManager();
		mLogManager.addEventLog("");
		mLogManager.addEventLog("");
		mLogManager.addEventLog("****** " + CVersion.kServerName +   "  Starting .... ******");

		/*
		 * Check the preferences file for the database setting, if we don't have
		 * db settings we have to ask the user
		 */
		try {
			/* Instantiate preferences */
			setmPreferences(new CPreferences());
		} catch (Exception e) {
			mLogManager.addErrorLog("Broker Server : Could not instantiate CPreferences");
			CError.throwError(700);
		}

		

		// Instantiate all managers here
		mMessageManager = new CMessageManager();

		// This method initializes database, throws Exception if there are
		// errors
		//initializeDatabase();

		mConnectionManager = new CConnectionManager();

		// Initializes all the monitors
		//initializeMonitors();
	}
	
	/**
	 * Get the reference of the CLogManager object
	 */
	public CLogManager getLogManager() {
		return mLogManager;
	}
	
	/**
	 * Get the reference of the CMessageManager object
	 */
	public CMessageManager getMessageManager() {
		return mMessageManager;
	}
	

	/**
	 * Get the reference of the CConnectionManager object
	 */
	public CConnectionManager getConnectionManager() {
		return mConnectionManager;
	}
	
	/**
	 * Get the reference of the CConnectionManager object
	 */
	public boolean getPrintBlock() {
		return mPrintBlock;
	}
	
	
	
	
	
	public static void main( String[] args ) throws Exception {
    
        System.out.println( "Hello Broker Server!" );
        if (g_app != null) {
			CError.throwError(702);
		}

		g_app = new BrokerSimulator();
		g_app.getConnectionManager().start();

		System.out.println();
		System.out.println("---- Broker Simulator Server Stopped ----");
		System.exit(0);
    }

	public CPreferences getmPreferences() {
		return mPreferences;
	}

	public void setmPreferences(CPreferences mPreferences) {
		this.mPreferences = mPreferences;
	}
}
