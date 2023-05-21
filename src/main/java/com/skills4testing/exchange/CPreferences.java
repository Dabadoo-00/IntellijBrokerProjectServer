package com.skills4testing.exchange;






public class CPreferences {
	// Preferences file constants
	public static final String keyDbType = "DatabaseType";
	public static final String keyDbDriver = "DatabaseDriver";
	public static final String keyDatabaseURL = "DatabaseURL";
	public static final String keyUser = "DatabaseUser";
	public static final String keyUserPassword = "DatabaseUserPassword";

	// Others
	public static String keyDebugOn = "keyDebugOn";
	public static String keyClientName = "keyClientName";
	public static String kLicenseKey = "kLicenseKey";
	public static String keySMTPHost = "keySMTPHost";
	public static String keySMTPPort = "keySMTPPort";
	public static String keyMCICURL = "keyMCICURL";
	public static String kMAX_LDAP_STAKEHOLDER_SIZE = "MAX_LDAP_STAKEHOLDER_SIZE";
	public static String K_ADMIN = "Administrator";
	private static String kPreferenceFilename = "preferences.dat";
	private static String kCurrentWorkingDir = "user.dir";
	private static String K_MESSAGE_PATH = "messages";
	private static String k_MESSAGE_HAND_PATH = "messagehandlers";
	public static String kTrue = "true";
	public static String kFalse = "false";
	private static int kDefaultSoapPortNum = 6382; // Default is 6382
	public static String kRRSDefaultEmailAddress = "response@skills4testing.com";
//	public static final String kMDSVersion = CVersion.vCurrentMDSVersion;
	//public static final String kMDSVersionID = "#MDSVERSION";

	public static final byte MDSDB_ENCODING_LATIN1 = 1;
	public static final byte MDSDB_ENCODING_UNICODE = 2;
	public static final byte MDSDB_ENCODING_SELECTED = MDSDB_ENCODING_UNICODE;
	public static final String XML_ENCODING_UNICODE = "utf-8";
	public static final String XML_ENCODING_LATIN1 = "ISO-8859-1";
	public static final String XML_ENCODING_SELECTED = XML_ENCODING_UNICODE;

	/*
	 * public static final String kMPMVersion =
	 * "Metatude Project Manager R1.2 Final [1.2.51]"; public static final
	 * String kMDDVersion = "Metatude Dialogue Designer R1.2 Final [1.2.33]";
	 * public static final String kMCIC_IISVersion =
	 * "MCIC IIS R1.2 Final [1.2.23]"; public static final String
	 * kMCIC_ApacheVersion ="MCIC Apache R1.2 Final [1.2.30]";
	 */


	
	public static int kProjectMonitorSession = 1000 * 60 * 9;
	public static int kSessionMonitorSession = 1000 * 60 * 2;
	public static int kDirServerMonitorSession = 1000 * 60 * 60 * 3;
	public static int kLicenceKeyMonitorSession = 1000 * 60 * 60 * 24; // 24
																		// hours
	public static int kStakeholderSize = 5500; // to remove this
	private static int MAX_LDAP_SIZE = 5500; // Default
	public static int DFO_MONITOR_ACTIVATION_PERIOD = 1000 * 60 * 60 * 4; // 4
																			// hours
	public static int DFO_MONITOR_ACTIVATION_LIMIT = 6;
	public static int DFO_DELETE_DATA_AFTER = 1000 * 60 * 60 * 24 * 10; // 10
																		// days
	
	public static boolean errorOn;
	public static boolean iDebugOn; // Invitation monitor mail specific debug
	public static boolean monitorStatusToLogDebugOn; // Invitation monitor mail
														// specific debug
	public static String mDbType;
	public static String mSMTPHost;
	public static int mSMTPPort;
	public static String mSMTPUserName;
	public static String mSMTPPassword;
	public static String mMcicUrl;
	public static String mAdminPassword;

	public static final String UTF8 = "UTF8";

	public static final String LATIN1 = "ISO-8859-1";


	// 1 == Only Metatude.
	// 2 == Only User.
	// 3 == Both Metatude & User
	// 4 == None.
	public static int mRRSStatus;// zahid(30/12/2003)
	public static String mRRSUserEmail;
	public static int mProjectMonitorSession;
	public static int mInvitationMonitorSession;
	public static int mDirServerMonitorSession;
	public static int mSessionMonitorSession;
	public static int mInvitationIntervalTime;
	public static int mLicensekeyIntervalTime;
	// public static int mMaxStakeHolderSize = kStakeholderSize; // Default size
	// for LDAP stakeholder
	public static String mApplicationDirectory;
	private int mDFOActivationPeriod;
	private int mDFOActivationLimit;
	private int mDFODeleteInterval;
	public boolean mUpdateDBPreferences = false;

	// public static String kMcicURL;
	//private boolean DEFAULT_M_DEBUG_ON = false;
//	CLogManager logManager;
	public int mSoapPortNum;
	public String mMessagePath;
	public String mActionPath;
	public String mDbDriver;
	public String mDbURL;
	public String mDbUser;
	public String mDbUserPassword;
	public String mDbName;
	public String mDbRegNo;
	private String mClientName; // User of a license key.
	private String mLicenseKey; // License key for this specific server
	//private Properties mProperties = new Properties();
	private long mMaxUserSessionIdle; // Maximum number of millisecs that a user
										// session is allowed to be idle
	private long mMaxLockTime; // Maximum number of millisecs that a lock is
								// valid
	private int mMaxLDAPSize;
//	public CLicenseKey mLicense;

	/**
	 * constructor().
	 */
	public CPreferences() throws Exception {
		mApplicationDirectory = System.getProperty(kCurrentWorkingDir);
		mSoapPortNum = kDefaultSoapPortNum;
//		loadFilePreferences();

		mRRSStatus = 4;
		mRRSUserEmail = "";

		mMaxUserSessionIdle = 15 * 60 * 1000; // 15 minutes in millisecs
		mMaxLockTime = 10 * 60 * 1000; // 10 minutes in millisecs
//		mInvitationIntervalTime = kInvitationIntervalTime;
//		mInvitationMonitorSession = kInvitationMonitorSession;
		mProjectMonitorSession = kProjectMonitorSession;
		mDirServerMonitorSession = kDirServerMonitorSession;
		mSessionMonitorSession = kSessionMonitorSession;
		mLicensekeyIntervalTime = kLicenceKeyMonitorSession;

		mMaxLDAPSize = MAX_LDAP_SIZE;

		mDFOActivationPeriod = DFO_MONITOR_ACTIVATION_PERIOD;
		mDFOActivationLimit = DFO_MONITOR_ACTIVATION_LIMIT;
		mDFODeleteInterval = DFO_DELETE_DATA_AFTER;

//		mLicense = new CLicenseKey();
//		mLicense.mClientName = mClientName;
	}

	/**
	 * getApplicationDirectory()
	 */
	public String getApplicationDirectory() {
		return mApplicationDirectory;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param lKey
	 *            DOCUMENT ME!
	 */
	public void setRegistrationKey(String lKey) {
		mLicenseKey = lKey;
	}

	/**
	 * getLicenseKey()
	 */
	public String getLicenseKey() {
		return mLicenseKey;
	}

	/**
	 * getDBType()
	 * 
	 */
	public String getDBType() {
		return CPreferences.mDbType;
	}

	/**
	 * setDBType()
	 */
	public void setDBType(String dbType) {
		CPreferences.mDbType = dbType;
	}

	/**
	 * getDbName()
	 * 
	 * Get Db Function Return the Database name.
	 */
	public String getDBName() {
		return this.mDbName;
	}

	/**
	 * setDbName()
	 * 
	 * Set the Database name.
	 */
	public void setDBName(String dbName) {
		this.mDbName = dbName;
	}

	/**
	 * getUserName()
	 */
	public String getDBUserName() {
		return this.mDbUser;
	}

	/**
	 * setUserName()
	 */
	public void setDBUserName(String userName) {
		this.mDbUser = userName;
	}

	/**
	 * getDBPassword()
	 */
	public String getDBPassword() {
		return this.mDbUserPassword;
	}

	/**
	 * setDBPassword()
	 */
	public void setDBPassword(String password) {
		this.mDbUserPassword = password;
	}

	/**
     *
     */
	public String getDBDriver() {
		return this.mDbDriver;
	}

	/**
	 * setDBDriver()
	 */
	public void setDBDriver(String dbDriver) {
		this.mDbDriver = dbDriver;
	}

	/**
	 * getDBUrl()
	 */
	public String getDBUrl() {
		return this.mDbURL;
	}

	/**
	 * Set DBUrl
	 */
	public void setDBUrl(String dbUrl) {
		this.mDbURL = dbUrl;
	}

	/**
	 * Get Registration Key -- Saadat includes
	 */
	public String getBDRegistrationKey() {
		return this.mDbRegNo;
	}

	/**
	 * Set Registration Key
	 */
	public void setBDRegistrationKey(String RegKey) {
		this.mDbRegNo = RegKey;
	}

	/**
	 * Get SMTP Host
	 */
	public String getSMTPHost() {
		return mSMTPHost;
	}

	/**
	 * Set Host
	 */
	public void setSMTPHost(String host) {
		mSMTPHost = host;
	}

	/**
	 * Get SMTP Port
	 */
	public int getSMTPPort() {
		return mSMTPPort;
	}

	/**
	 * Set SMTP port
	 */
	public void setSMTPPort(int port) {
		mSMTPPort = port;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getSMTPUserName() {
		return mSMTPUserName;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param smtpUser
	 *            DOCUMENT ME!
	 */
	public void setSMTPUserName(String smtpUser) {
		mSMTPUserName = smtpUser;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getSMTPPassword() {
		return mSMTPPassword;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param smtpPassword
	 *            DOCUMENT ME!
	 */
	public void setSMTPPassword(String smtpPassword) {
		mSMTPPassword = smtpPassword;
	}

	/**
	 * Get mcic url -- Saadat includes
	 */
	public String getMCICUrl() {
		return mMcicUrl;
	}

	/**
	 * Set Host
	 */
	public void setMcicUrl(String mcic) {
		mMcicUrl = mcic;
	}

	/**
	 * Returns soap port number to listen to the socket.
	 */
	public int getSoapPortNumber() {
		return mSoapPortNum;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param cName
	 *            DOCUMENT ME!
	 */
	public void setClientName(String cName) {
		mClientName = cName;
	}

	/**
	 * DOCUMENT ME!
	 */
//	public void setClientNameForLiceseKey() {
//		mLicense.mClientName = mClientName;
//	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getClientName() {
		return mClientName;
	}

	/**
	 * 
	 * @return
	 */
	public int getRRSStatus() {// zahid(30/12/2003)
		return mRRSStatus;
	}

	/**
	 * 
	 * @param rrsStatus
	 */
	public void setRRSStatus(int rrsStatus) {// zahid(30/12/2003)
		mRRSStatus = rrsStatus;
	}

	/**
	 * 
	 * @return
	 */
	public String getRRSUserEmail() {
		return mRRSUserEmail;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setRRSUserEmail(String emailAddress) {
		mRRSUserEmail = emailAddress;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public String getDefaultEmailAddress() {
		return kRRSDefaultEmailAddress;
	}

	/**
	 * getMessageClassPath() Get the path of the message to load.
	 * 
	 */
	public String getMessageClassPath() {
		String fileSeparator = System.getProperty("file.separator");

		return getApplicationDirectory() + fileSeparator + CPreferences.K_MESSAGE_PATH
				+ fileSeparator;
	}

	/**
	 * getMessageHandlerPath() Get the path of the messageHandler to load.
	 */
	public String getMessageHandlerPath() {
		String fileSeparator = System.getProperty("file.separator");

		return getApplicationDirectory() + fileSeparator
				+ CPreferences.k_MESSAGE_HAND_PATH + fileSeparator;
	}

	/**
	 * getPreferenceFilePath()
	 * 
	 */
	public String getPreferenceFilePath() {
		String fileSeparator = System.getProperty("file.separator");

		return getApplicationDirectory() + fileSeparator + kPreferenceFilename;
	}

	/**
	 * 
	 * @return
	 */
	public int getStakeholderSize() {
		return mMaxLDAPSize;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param stakeholderSize
	 *            DOCUMENT ME!
	 */
	public void setStakeholderSize(int stakeholderSize) {
		mMaxLDAPSize = stakeholderSize;
	}

	/**
	 * setDBPreferenceUpdate()
	 */
	public void setDBPreferenceUpdate(boolean val) {
		mUpdateDBPreferences = val;
	}

	/**
	 * 
	 * @param dfoInterval
	 */
	public void setDFOActivationPeriod(int dfoInterval) {
		this.mDFOActivationPeriod = dfoInterval;
	}

	/**
	 * 
	 * @return
	 */
	public int getDFOActivationPeriod() {
		return mDFOActivationPeriod;
	}

	/**
	 * 
	 * @param dfoaLimit
	 */
	public void setDFOActivationLimit(int dfoaLimit) {
		mDFOActivationLimit = dfoaLimit;
	}

	/**
	 * 
	 * @return
	 */
	public int getDFOActivationLimit() {
		return mDFOActivationLimit;
	}

	/**
	 * 
	 * @param dfoDeleteInt
	 */
	public void setDFODeleteInterval(int dfoDeleteInt) {
		this.mDFODeleteInterval = dfoDeleteInt;
	}

	/**
	 * 
	 * @return
	 */
	public int getDFODeleteInterval() {
		return mDFODeleteInterval;
	}



	/**
	 * getMaxUserSessionIdle
	 */
	public long getMaxUserSessionIdle() {
		return mMaxUserSessionIdle;
	}

	/**
	 * setMaxUserSessionIdle
	 */
	public void setMaxUserSessionIdle(long value) {
		mMaxUserSessionIdle = value;
	}

	/**
	 * getMaxLockTime
	 */
	public long getMaxLockTime() {
		return mMaxLockTime;
	}

	/**
	 * setMaxLockTime
	 */
	public void setMaxLockTime(long value) {
		mMaxLockTime = value;
	}



	/**
	 * validate()
	 * 
	 * 
	 * this method checks the presents of preference file
	 * 
	 */
	public boolean validate() {
		if ((mDbType == null) | (mDbDriver == null) | (mDbURL == null)) {
			return false;
		} else if (mDbType.equals("") | mDbDriver.equals("")
				| mDbURL.equals("")) {
			return false;
		}

		return true;
	}

	

}

