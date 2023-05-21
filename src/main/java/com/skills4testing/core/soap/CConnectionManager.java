package com.skills4testing.core.soap;

public class CConnectionManager {

	public CSoapSocket mSoapSocket;

	private int portNum;

	/**
	 * Constructor
	 */
	public CConnectionManager() {
		portNum = 6688; //CWICTCEServer.g_app.getPreferences().getSoapPortNumber();
	}

	/**
	 * Starts the listening
	 */
	public void start() throws Exception {

		mSoapSocket = new CSoapSocket();
		mSoapSocket.start(portNum);
	}

	/**
	 * Stops the listening
	 */
	public void stop() throws Exception {
		try {
			mSoapSocket.stop();
		} catch (Exception e) {
			//CWICTCEServer.g_app.getLogManager().addErrorLog("CCoonectionManager.stop() Error :: " + e.toString());
			e.printStackTrace();
		}
	}
}
