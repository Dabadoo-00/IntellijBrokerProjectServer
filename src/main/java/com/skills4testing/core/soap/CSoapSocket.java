package com.skills4testing.core.soap;

import java.net.*;

import com.skills4testing.core.conn.CSoapConnectionHandler;
import com.skills4testing.core.log.CLogManager;
import com.skills4testing.core.util.CDateTime;
import com.skills4testing.core.util.CError;
import com.skills4testing.exchange.BrokerSimulator;
import com.skills4testing.exchange.CVersion;


public class CSoapSocket {

	private Socket mSocket;

	private ServerSocket mServerSocket;

	private boolean mDone = false;

	private static ThreadGroup mThreadGroup;

	private String mThreadGroupName = "SoapConnThreads";

	private static String mIPAddress = "";
	
	//private static boolean debugOn = BrokerSimulator.mDebugOn; 
	
	private static boolean mPrintBlock = BrokerSimulator.mPrintBlock;
	
	private CLogManager mLogManager = BrokerSimulator.g_app.getLogManager();

	/**
	 * Constructor
	 */
	public CSoapSocket() {
	}

	/**
	 * Constructor
	 */
	public void start(int port) throws Exception {

		mThreadGroup = new ThreadGroup(mThreadGroupName);

		try {
			mServerSocket = new ServerSocket(port);
		} catch (BindException be) {
			System.out.println("Port " + port + " is in use.");
			System.out.println("Shutting down...");
			for (int i = 0; i < 100000000; i++)
				;
			//CWICTCEServer.g_app.getLogManager().addEventLog("Port " + port + " is in use.");
			System.exit(1);
		}

		System.out.println("-----------------------------------------------------------------");
		System.out.println(CVersion.vCurrentServerReleaseName   + port);
		System.out.println(CVersion.vCurrentServerVersion + ".");
		System.out.println("-----------------------------------------------------------------");
		/*
		 * if (debugOn) {
		 * System.out.println(" This version is compatible with...");
		 * System.out.println("  "+CPreferences.kMPMVersion);
		 * System.out.println("  "+CPreferences.kMDDVersion);
		 * System.out.println("  "+CPreferences.kMCIC_IISVersion);
		 * System.out.println("  "+CPreferences.kMCIC_ApacheVersion);
		 * System.out.println("  "); }
		 */
		mLogManager.addEventLog(CVersion.vCurrentServerVersion + " Started.");
		mLogManager.addEventLog("");
		mLogManager.addErrorLog(CVersion.vCurrentServerVersion + " Started.");
		mLogManager.addErrorLog("");
		/*
		 * CDialogueServer.g_app.getLogManager().addEventLog(
		 * "This version is compatible with ");
		 * CDialogueServer.g_app.getLogManager
		 * ().addEventLog(" "+CPreferences.kMPMVersion);
		 * CDialogueServer.g_app.getLogManager
		 * ().addEventLog(" "+CPreferences.kMDDVersion);
		 * CDialogueServer.g_app.getLogManager
		 * ().addEventLog(" "+CPreferences.kMCIC_IISVersion);
		 * CDialogueServer.g_app
		 * .getLogManager().addEventLog(" "+CPreferences.kMCIC_ApacheVersion);
		 */

		while (!mDone) {
			try {
				mSocket = mServerSocket.accept();
				mIPAddress = mSocket.getInetAddress().getHostAddress();
				if (!mPrintBlock)
					System.out.println(CVersion.kServerName + CDateTime.toSystemOutDate()
							+ ">"
							+ CVersion.kConnectionReceivedFrom
							+ mSocket.getInetAddress().getHostAddress());
				if (mSocket != null) {
					//CSoapConnectionHandler soapConnHandler = new CSoapConnectionHandler(mSocket);
					CSoapConnectionHandler soapConnHandler = new CSoapConnectionHandler();
					soapConnHandler.setClientSocket(mSocket);
					Thread client = new Thread(mThreadGroup, soapConnHandler,"Soap Connection Handler");
					client.start();

					// test for thread group
					// if (mThreadGroup.activeCount() > 0){
					// if (debugOn) {
					// System.out.println("There are " +
					// mThreadGroup.activeCount() +
					// " simultaneous thread(s) running exclusive monitors.");
					// }
					// }

				}
			} catch (Exception e) {

				// Connection could not be established
				if (mDone == true) {
					return;
				}
				mLogManager.addErrorLog("CSoapSocket.start() Error CError(703) :: "+ e.toString());
				CError.throwError(703);
			}
		}
	}

	/**
	 * shut down
	 */
	public void stop() throws Exception {
		mDone = true;
		mServerSocket.close();
		System.out.println("Closing All The Message Handlers.");
		while (mThreadGroup.activeCount() > 0) {
			System.out.print(".");
			Thread.yield();
		}
		return;
	}

	/**
	 * getThreadCount
	 * 
	 * returns the ThreadGroup active Thread count
	 */
	public static int getThreadCount() {
		return mThreadGroup.activeCount();
	}

	/**
	 * showActiveThreadNames
	 * 
	 * 
	 */
	public static void showActiveThreadNames() {
		if (mThreadGroup.activeCount() > 0) {
			mThreadGroup.list();
		}
	}

	/**
	 * getThreadCount
	 * 
	 * returns the ThreadGroup active Thread count
	 */
	public static boolean isCSoapConnectionHandlerInPool() {
		Thread threadList[] = new Thread[mThreadGroup.activeCount()];
		if (mThreadGroup.activeCount() > 0) {
			mThreadGroup.enumerate(threadList);
			for (int i = 0; i < threadList.length; i++) {
				// CUtils.toSystemMessage("Thread Name : "+threadList[i].getName());
				if (threadList[i].getName().equals("Soap Connection Handler"))
					return true;
			} // for

		} // if
		return false;
	}

	/**
	 * getThreadCount
	 * 
	 * returns the ThreadGroup active Thread count
	 */
	/*
	 * public static void clearSoapConnHandlerFromThreadGroup(){ Thread
	 * threadList[] = new Thread[mThreadGroup.activeCount()]; if
	 * (mThreadGroup.activeCount() > 0){ mThreadGroup.enumerate(threadList); for
	 * (int i=0;i<threadList.length;i++){ if
	 * (threadList[i].getName().equals("Soap Connection Handler")){
	 * CUtils.toSystemMessage("Kill Thread : "+threadList[i].getName());
	 * threadList[i] = null; } } // for } // if }
	 */
	public static String getIPAddress() {
		return mIPAddress;
	}

}
