package com.skills4testing.core.message;

/**
 * class MsgConst
 * 
 * All constants for the com.metatude.mds.msg are kept in this class.
 * 
 */

public final class MsgConst {
	public static final String kSTART_TAG_ON = "<";
	public static final String kEND_TAG_ON = "</";
	public static final String kTAG_OF = ">";
	public static final String kCRLF = "";
	public static final String kDirectoryDelimiter = "/";
	public static final String kFamily = "Family";
	public static final String kMessage = "Message";

	// User Rights.
	public static final int kAdministratorRights = 1;
	public static final int kUserRights = 2;

	// Project status
	public static final int kProjectInactive = 1;
	public static final int kProjectActive = 2;
	public static final int kProjectWaitingForStart = 3;
	public static final int kProjectPaused = 4;
	public static final int kProjectCompleted = 5;
	public static final int kProjectDeleted = 6;

	// Mail sent interaction mapping
	public static final int kMailSentMapping = 1;
	public static final int kDialogueSentMapping = 3;
	public static final int kDialogueSubmittedMapping = 4;
	public static final int kMailSentInOtherTGMapping = 5;
	public static final int kMailCancelledOnceMapping = 11;
	public static final int kMailCancelledTwiceMapping = 12;
	public static final int kMailCancelledThriceMapping = 13;

	// Table names to use with query
	public static final String kTableStrRespntIntnHty = "RespondentInteractionHistory";
	public static final String kTableStrReminderHty = "ReminderHistory";

	// Connection family Message.
	public static final String kGetVersion = "GetVersion";
	public static final String kConnectionFamily = "Connection";
	public static final String kMessageType = "LoginResponse";
	public static final String kLogin = "Login";
	public static final String kLogout = "Logout";
	public static final String kPing = "Ping";
	
	// Connection family Message.
	public static final String kExecuteOrderFamily = "ExecuteOrder";
	public static final String kExecute = "Execute";
	public static final String kExecuteResponse = "ExecuteResponse";
		
		
	
	// --------------------------------------------
	public static final String GET_ENCODING = "GetMDSEncoding";
	// ----- ADDED END BY ZAHID : 16-03-2004
	// ----------------------------------------

	// Users family message
	public static final String kUsers = "Users";
	public static final String kAddUserDir = "AddUserDir";
	public static final String kEditUserDir = "EditUserDir";
	public static final String kDeleteUserDir = "DeleteUserDir";
	public static final String kMoveUserDir = "MoveUserDir";
	public static final String kMoveUser = "MoveUser";
	public static final String kAddUserDirResponse = "AddUserDirResponse";
	public static final String kEditUserDirResponse = "EditUserDirResponse";
	public static final String kDeleteUserDirResponse = "DeleteUserDirResponse";
	public static final String kMoveUserResponse = "MoveUserResponse";
	public static final String kMoveUserDirResponse = "MoveUserDirResponse";

	public static final String kDeleteQuery = "Delete";
	public static final String kGetQuery = "Get";
	public static final String kAbortQuery = "Abort";
	public static final String kListQuery = "List";

	public static final String kReleaseLockQuery = "ReleaseLock";

	public static final String kGeneralMsgType = "GeneralResponse";

	// Project family messages
	public static final String kProjectsFamily = "Projects";
	public static final String kProject = "Project";

	public static final String kModifyProjectQuery = "ModifyProject";
	public static final String kAddProjectQuery = "AddProject";
	public static final String kGetProjectResponse = "GetProjectResponse";

	// Funder family messages
	public static final String kFundersFamily = "Funders";
	public static final String kFunder = "Funder";

	public static final String kModifyFunderQuery = "ModifyFunder";
	public static final String kAddFunderQuery = "AddFunder";
	public static final String kGetFunderResponse = "GetFunderResponse";

	// Partner family messages
	public static final String kPartnerFamily = "Partners";
	public static final String kPartner = "Partner";

	public static final String kModifyPartnerQuery = "ModifyPartner";
	public static final String kAddPartnerQuery = "AddPartner";
	public static final String kGetPartnerResponse = "GetPartnerResponse";

	// Beneficiary family messages
	public static final String kBeneficiaryFamily = "Beneficiary";
	public static final String kBeneficiary = "Beneficiary";

	public static final String kModifyBeneficiaryQuery = "ModifyBeneficiary";
	public static final String kAddBeneficiaryQuery = "AddBeneficiary";
	public static final String kGetBeneficiaryResponse = "GetBeneficiaryResponse";

	public static final String kAdvancedSearchQuery = "AdvancedSearch";
	public static final String kAdvancedSearchRespomse = "AdvancedSearchResponse";

	// Fund family messages
	public static final String kFundFamily = "Fund";
	public static final String kFund = "Fund";

	public static final String kModifyFundQuery = "ModifyFund";
	public static final String kAddFundQuery = "AddFund";
	public static final String kGetFundResponseResponse = "GetFundResponse";

	// Service family messages
	public static final String kServiceFamily = "Service";
	public static final String kService = "Service";

	public static final String kModifyServiceQuery = "ModifyService";
	public static final String kAddServiceQuery = "AddService";
	public static final String kGetServiceResponseResponse = "GetServiceResponse";

	// Staff family messages
	public static final String kStaffFamily = "Staff";
	public static final String kStaff = "Staff";

	public static final String kModifyStaffQuery = "ModifyStaff";
	public static final String kAddStaffQuery = "AddStaff";
	public static final String kGetStaffResponse = "GetStaffResponse";

	// Appointment family messages
	public static final String kAppointmentFamily = "Appointment";
	public static final String kAppointment = "Appointment";

	public static final String kModifyAppointmentQuery = "ModifyAppointment";
	public static final String kAddAppointmentQuery = "AddAppointment";
	public static final String kGetAppointmentResponse = "GetAppointmentResponse";

	// User family messages
	public static final String kUserFamily = "Users";
	public static final String kAddUserQuery = "AddUser";
	public static final String kEditUserQuery = "EditUser";

	public static final String kUser = "User";
	public static final String kGetUserQuery = "GetUser";
	public static final String kGetUserResponse = "GetUserResponse";

	public static final String kInvalidSession = "InvalidSession";

	// Category family messages
	public static final String kCategoryFamily = "Category";
	public static final String kCategory = "Category";

	public static final String kAddCategory = "AddCategory";
	public static final String kListCategory = "ListCategory";
	public static final String kModifyCategory = "ModifyCategory";

	// Sub-Category family messages
	public static final String kSubCategoryFamily = "SubCategory";
	public static final String kSubCategory = "SubCategory";

	public static final String kAddSubCategory = "AddSubCategory";
	public static final String kListSubCategory = "ListSubCategory";
	public static final String kModifySubCategory = "ModifySubCategory";

	// Code family messages

	public static final String kCodeFamily = "Code";
	public static final String kAddCode = "AddCode";

	public static final String kListcode = "GetCodeList";
	public static final String kModifyCode = "ModifyCode";

	// Report family messages

	public static final String kReport = "Report";

	public static final String kGetProjectReport = "GetProjectReport";
	public static final String kGetProjectReportResponse = "GetProjectReportResponse";

	public static final String kListReportSummaryQuery = "ListReportSummaryQuery";

	// public static final String kModifySubCategory = "ModifySubCategory";

	// public static final String kGetProjectQuery = "GetProject";
	//
	// public static final String GET_PROJECT_INFO_QUERY = "GetProjectInfo";
	// public static final String GET_PROJECT_INFO_RESPONSE =
	// "GetProjectInfoResponse";

	// public static final String kEditProjectResponse = "EditProjectResponse";
	// public static final String kListProjectsQuery = "ListProjects";
	// public static final String kListProjectsResponse =
	// "ListProjectsResponse";

	// public static final String kAddProjectResponse = "AddProjectResponse";
	// public static final String kDeleteProjectQuery = "DeleteProject";
	// public static final String kDeleteProjectResponse =
	// "DeleteProjectResponse";
	// public static final String kCancelProjectQuery = "CancelProject";
	// public static final String kCancelProjectResponse =
	// "CancelProjectResponse";
	// public static final String kSetProjectDialogueQuery =
	// "SetProjectDialogue";
	// public static final String kSetProjectDialogueResponse =
	// "SetProjectDialogueResponse";
	// public static final String kSetProjectInvitationQuery =
	// "SetProjectInvitation";
	// public static final String kSetProjectInvitationResponse =
	// "SetProjectInvitationResponse";
	// public static final String kAddProjectDir = "AddProjectDir";
	// public static final String kAddProjectDirResponse =
	// "AddProjectDirResponse";
	// public static final String kEditProjectDir = "EditProjectDir";
	// public static final String kEditProjectDirResponse =
	// "EditProjectDirResponse";
	// public static final String kMoveProject = "MoveProject";
	// public static final String kMoveProjectResponse = "MoveProjectResponse";
	// public static final String kMoveProjectDir = "MoveProjectDir";
	// public static final String kMoveProjectDirResponse =
	// "MoveProjectDirResponse";
	// public static final String kDeleteProjectDir = "DeleteProjectDir";
	// public static final String kDeleteProjectDirResponse =
	// "DeleteProjectDirResponse";

	// Crypto Family
	public static final String kCryptoFamily = "Crypto";
	public static final String kGetPublicKey = "GetPublicKey";
	public static final String kGetPublicKeyResponse = "GetPublicKeyResponse";

	// Reminder family messages
	public static final String kReminder = "Reminder";
	public static final String kReminders = "Reminders";
	public static final String kReminderName = "ReminderName";
	public static final String kRemindAfter = "RemindAfter";
	public static final String kRemindStatus = "Status";
	public static final String kRemindNature = "RemindNature";
	public static final String kFrom = "From";
	public static final String kSubject = "Subject";
	public static final String kBody = "Body";
	public static final String kMailSent = "MailSent";
	public static final String kReminderBody = "ReminderBody";
	public static final String kReminderID = "ReminderID";
	public static final String kRemindersFamily = "Reminder";
	public static final String kAddReminder = "AddReminders";
	public static final String kAddReminderResponse = "AddReminderResponse";
	public static final String kEditReminder = "EditReminder";
	public static final String kEditReminderResponse = "EditReminderResponse";
	public static final String kGetReminder = "GetReminder";
	public static final String kGetReminderResponse = "GetReminderResponse";
	public static final String kDeleteReminder = "DeleteReminder";
	public static final String kDeleteReminderResponse = "DeleteReminderResponse";
	public static final String kListReminders = "ListReminders";
	public static final String kListRemindersResponse = "ListRemindersResponse";

	// Reminder status
	public static final Integer kReminderStatusNow = new Integer(0);
	public static final Integer kReminderStatusScheduled = new Integer(1);
	public static final Integer kReminderStatusInProgress = new Integer(2);
	public static final Integer kReminderStatusPaused = new Integer(3);
	public static final Integer kReminderStatusCompleted = new Integer(4);
	public static final Integer kReminderStatusInacive = new Integer(5);

	// Target Group family messages
	public static final String kTargetGroups = "TargetGroups";
	public static final String kTargetGroup = "TargetGroup";
	public static final String kAddTargetGroupDir = "AddTargetGroupDir";
	public static final String kEditTargetGroupDir = "EditTargetGroupDir";
	public static final String kDeleteTargetGroupDir = "DeleteTargetGroupDir";
	public static final String kMoveTargetGroup = "MoveTargetGroup";
	public static final String kMoveTargetGroupDir = "MoveTargetGroupDir";
	public static final String kListTargetGroupsResponse = "ListTargetGroupsResponse";
	public static final String kTargetGroupID = "TargetGroupID";
	public static final String kTargetDirectory = "TargetDirectory";
	public static final String kTargetGroupName = "TargetGroupName";
	public static final String kAll = "All";
	public static final String kIsAllSelected = "isAllSelected";

	// XML field strings
	public static final String kSessionID = "SessionID";
	public static final String kProjectID = "ProjectID";
	public static final String kField = "Field";
	public static final String kLock = "Lock";
	public static final String kProjectName = "ProjectName";
	public static final String kDialogueID = "DialogueID";
	public static final String kDialogueName = "DialogueName";
	public static final String kDirectoryName = "DirectoryName";
	public static final String kNewDirectoryName = "NewDirectoryName";
	public static final String kDirServerID = "DirServerID";
	public static final String kDirServerName = "DirServerName";
	public static final String kDescription = "Description";
	public static final String kQuery = "Query";
	public static final String kSize = "Size";
	public static final String kStatus = "Status";
	public static final String kStartAtDate = "StartAtDate";
	public static final String kSampleSize = "SampleSize";
	public static final String kInvitationID = "InvitationID";
	public static final String kInvite = "Invite";
	public static final String kResultCount = "ResultCount";
	public static final String kLockStatus = "LockStatus";
	public static final String kLockReleaseDate = "LockReleaseDate";
	public static final String kLockedBy = "LockedBy";
	public static final String kResults = "Results";
	public static final String kPort = "Port";
	public static final String kProjectType = "ProjectType";
	public static final String kProjectEndDate = "ProjectEndDate";
	public static final String kIntervalType = "IntervalType";
	public static final String kInviteAfter = "InviteAfter";
	public static final String kCRResultCounts = "CRResultCounts";
	public static final String kCRResultCount = "CRResultCount";
	public static final String kStartDate = "StartDate";
	public static final String kProjectDescription = "Description";
	public static final String kProjectInterval = "Interval";
	public static final String kEndDate = "EndDate";
	public static final String kPrjIntervalMailCount = "MailCount";
	public static final String kPrjIntervalName = "IntervalName";
	public static final String kCRResults = "Results";
	public static final String kIntervalName = "IntervalName";
	public static final String INTERVAL_NO = "IntervalNo";
	public static final String kReadableName = "ReadableName";
	public static final String kTranslation = "Translation";
	public static final String kValueOfTheServer = "ValueOfTheServer";
	public static final String kHost = "Host";
	public static final String kBase = "Base";
	public static final String kUserName = "UserName";
	public static final String kUserPassword = "UserPassword";
	public static final String kInvitationFrom = "From";
	public static final String kCustomAttributeName = "CustomAttributeName";
	public static final String CUSTOM_ATTRIBUTES = "CustomAttributes";
	public static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
	// public static final String kSubject = "Subject";

	// XML Field values
	public static final String kLockStatusFree = "0"; // "Free";
	public static final String kLockStatusDenied = "-1"; // "Denied";
	public static final String kLockStatusLocked = "1"; // "Locked";
	public static final String kLockStatusUnlocked = "-99"; // "Unlocked";

	public static final String kProjectStatusInactive = "Inactive";
	public static final String kProjectStatusActive = "Active";
	public static final String kProjectStatusWaiting = "StartAt";
	public static final String kProjectStatusPaused = "Paused";
	public static final String kProjectStatusCompleted = "Completed";
	public static final String kProjectStatusDeleted = "Deleted";
	public static final String kProjectStatusCancel_1 = "Cancel - 1";
	public static final String kProjectStatusCancel_2 = "Cancel - 2";
	public static final String kProjectStatusCancel_3 = "Cancel - 3";

	// Directory Server Status
	public static final String kDirectoryServerAvailable = "Available";
	public static final String kDirectoryServerNotAvailable = "NotAvailable";
	public static final String kDirectoryServerErrorAttrMatch = "ErrorAttrMatch";

	public static final String kLDAP = "LDAP";
	public static final String kJDBC = "JDBC";

	// Attribute Type.
	public static final String kOrgAttributeStringType = "String";
	public static final String kOrgAttributeNumberType = "Number";
	public static final String kOrgAttributeDateType = "Date";
	public static final String kOrgAttributeBooleanType = "Boolean";

	public static final String kUserAttributeStringType = "String";
	public static final String kUserAttributeNameType = "Name";
	public static final String kUserAttributeEmailType = "Email";
	public static final String kUserAttributeNumberType = "Number";
	public static final String kUserAttributeDateType = "Date";
	public static final String kUserAttributeBooleanType = "Boolean";
	public static final String kUserAttributeDOBType = "Date Of Birth";

	public static final String kURL = "url";
	// public static final String kUser = "user";

	public static final String kResult = "Result";
	public static final String kOk = "OK";
	public static final String kName = "Name";
	public static final String kValue = "Value";

	// Targetgroup family messages
	public static final String kTargetgroupsFamily = "TargetGroups";
	public static final String kGetTargetGroup = "GetTargetGroup";
	public static final String kListTargetGroups = "ListTargetGroups";
	public static final String kAddTargetGroup = "AddTargetGroup";
	public static final String kEditTargetGroup = "EditTargetGroup";
	public static final String kDeleteTargetGroup = "DeleteTargetGroup";
	public static final String kCancelTargetGroup = "CancelTargetGroup";
	public static final String kGetTargetGroupCount = "GetTargetGroupCount";
	public static final String kGetTargetGroupCountResponse = "GetTargetGroupCountResponse";
	public static final String kGetTargetGroupRecordsQuery = "GetTargetGroupRecords";
	public static final String kGetTargetGroupRecordsResponse = "GetTargetGroupRecordsResponse";

	// Respondent family message;
	public static final String kRespondentFamily = "Respondent";
	public static final String kGetRespondentDialogueQuery = "GetRespondentDialogueQuery";
	public static final String kGetRespondentDialogueResponse = "GetRespondentDialogueResponse";
	public static final String kAddDialogueResultQuery = "AddDialogueResultQuery";
	public static final String kAddDialogueResultResponse = "AddDialogueResultResponse";
	public static final String kMCICVersion = "MCICVersion";

	// public static final String kDialogueID = "DialogueID";
	public static final String kRespondentID = "RespondentID";
	public static final String kDialogue = "Dialogue";
	public static final String kResponse = "Response";
	// public static final String kResult ="Result";
	public static final String kQuestion = "Question";

	// Dialogue family message
	public static final String kDialoguesFamily = "Dialogues";
	public static final String kAddDialogue = "AddDialogue";
	public static final String kGetDialogue = "GetDialogue";
	public static final String kDeleteDialogue = "DeleteDialogue";

	// Invitation family message
	public static final String kInvitationFamily = "Invitations";
	public static final String kAddInvitation = "AddInvitation";
	public static final String kGetInvitation = "GetInvitation";
	public static final String kDeleteInvitation = "DeleteInvitation";

	// Directory Server Family
	public static final String kDirServerFamily = "DirServers";
	public static final String DIRECTORY_SERVERS = "DirectoryServers";
	public static final String DIRECTORY_SERVER = "DirectoryServer";
	public static final String kGetDirServer = "GetDirServer";
	public static final String kListDirServers = "ListDirServers";
	public static final String kAddDirServer = "AddDirServer";
	public static final String kEditDirServer = "EditDirServer";
	public static final String kDeleteDirServer = "DeleteDirServer";
	public static final String kCancelDirServer = "CancelDirServer";
	public static final String kGetDirServerCount = "GetDirServerCount";
	public static final String kGetDirServerCountResponse = "GetDirServerCountResponse";
	public static final String kGetDirServerRecords = "GetDirServerRecords";
	public static final String kGetDirServerRecordsResponse = "GetDirServerRecordsResponse";

	public static final String kGetCustomAttribute = "GetCustomAttribute";
	public static final String kGetCustomAttributeResponse = "GetCustomAttributeResponse";
	public static final String kUpdateCustomAttribute = "UpdateCustomAttribute";
	public static final String kUpdateCustomAttributeResponse = "UpdateCustomAttributeResponse";
	public static final String kCancelCustomAttribute = "CancelCustomAttribute";
	public static final String kCancelCustomAttributeResponse = "CancelCustomAttributeResponse";

	// User action handler

	// public static final String kUserFamily = "Users" ;
	public static final String kGetUser = "GetUser";
	public static final String kAddUser = "AddUser";
	public static final String kEditUser = "EditUser";
	public static final String kDeleteUser = "DeleteUser";
	public static final String kListUsers = "ListUsers";
	public static final String kCancelUser = "CancelUser";

	// Result family.
	public static final String kResultFamily = "Results";
	public static final String kGetProjectResultCount = "GetProjectResultCount";
	public static final String kGetProjectResultCountResponse = "GetProjectResultCountResponse";
	public static final String kGetProjectResults = "GetProjectResults";
	public static final String kGetProjectResultsResponse = "GetProjectResultsResponse";
	public static final String kStartValue = "Start";
	public static final String kCountValue = "Count";
	public static final String kRecordCount = "RecordCount";
	public static final String kFields = "Fields";
	public static final String kRecords = "Records";
	public static final String kRecord = "Record";
	public static final String kRecordNumber = "RecordNumber";
	public static final String kNumber = "Number";
	public static final String kSubmissionDate = "SubmissionDate";
	public static final String kClearProjectResults = "ClearProjectResults";
	public static final String kClearProjectResultsResponse = "ClearProjectResultsResponse";

	// Result family.
	public static final String kInteractivity = "Interactivity";
	public static final String kRequestNext = "RequestNext";
	public static final String kRequestBack = "RequestBack";
	public static final String kCurrentPageNo = "CurrentPageNo";
	public static final String kCurrentSetNo = "StepNumber";
	public static final String kEndOfSurvey = "END OF SURVEY";
	public static final String kSubmitted = "Submitted";

	public static final String kRespName = "Name";
	public static final String kRespEmail = "Email";
	public static final String kFirstSet = "FirstSet";
	public static final String kAnsweredQuestions = "AnsweredQuestions";

	// Message for the respondent's browser. Message is changed on 18-03-2003,
	// Suggested by Anton.

	// public static final String kRspMsgCompletedProject =
	// "This survey is closed, therefore you cannot participate in this survey. Thanks for your cooperation.";
	public static final String kRspMsgCompletedProject = "\\iIt is no longer necessary for you to participate in this project.\\pThank you for your cooperation.\\i0";
	// public static final String kRspMsgWereInv =
	// "You were invited to participate based on your profile. Due to change in your profile it is no longer necessary for you to participate. Thanks for your cooperation.";
	public static final String kRspMsgWereInv = "\\iIt is no longer necessary for you to participate in this project.\\pThank you for your cooperation.\\i0";
	// public static final String kRspMsgAlreadySub =
	// "You already participated in this project. Thanks for your cooperation.";
	public static final String kRspMsgAlreadySub = "\\iYou already participated in this project.\\pThank you for your cooperation.\\i0";
	// public static final String kRspMsgNoDlg =
	// "The server couldn't locate the dialogue. The project is probably no longer available.";
	public static final String kRspMsgNoDlg = "\\iIt is no longer necessary for you to participate in this project.\\pThank you for your cooperation.\\i0";
	// public static final String kRspMsgNoPrj =
	// "The project is probably no longer available. Please inform the administrator of this site. Thanks for your cooperation.";
	public static final String kRspMsgNoPrj = "\\iIt is no longer necessary for you to participate in this project.\\pThank you for your cooperation.\\i0";
	// public static final String kRspMsgNoResultTbl =
	// "The server couldn't access the result database. Please inform the administrator of this site.";
	public static final String kRspMsgNoResultTbl = "\\iThe system is temporarily off-line. Please try again in a few minutes.\\pIf this error persists, please inform the coordinator of the project by sending a reply e-mail to your invitation.\\i0";
	// public static final String kRspMsgNoAccessResultTbl =
	// "The server couldn't store the result in the database. Please inform the administrator of this site.";
	public static final String kRspMsgNoAccessResultTbl = "\\iThe system is temporarily off-line. Please try again in a few minutes.\\pIf this error persists, please inform the coordinator of the project by sending a reply e-mail to your invitation.\\i0";
	// public static final String kRspMsgUnknown =
	// "An unknown error occurred processing your request. Please inform the administrator of this site.";
	public static final String kRspMsgUnknown = "\\iYour request could not be processed properly.\\pPlease copy the entire hyperlink to the survey from your invitation e-mail and paste it in the address bar of your browser.\\i0";
	// public static final String kRspMsgUnknownMCICVersion =
	// "This version of MCIC is not compatible with Metatude Product suit. Please inform the administrator of this site.";
	public static final String kRspMsgUnknownMCICVersion = "The system is not installed correctly. Please inform the coordinator of the project by sending a reply e-mail to your invitation.";
}
