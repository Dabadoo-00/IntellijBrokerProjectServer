package com.skills4testing.client;

public final class CConstants {
    public static final String MCIC = "MCIC";
    public static final String RESPONSE_CONTENT_TYPE_LATIN1 = "text/html;charset=iso-8859-1";
    public static final String RESPONSE_CONTENT_TYPE_UTF8 = "text/html; charset=utf-8";
    public static String RESPONSE_CONTENT_TYPE_SELECTED = RESPONSE_CONTENT_TYPE_UTF8;
    public static final String DIALOGUE_ENCODING_LATIN1 = "ISO-8859-1";
    public static final String DIALOGUE_ENCODING_UNICODE = "UTF-8";
    public static String DIALOGUE_ENCODING_SELECTED = DIALOGUE_ENCODING_UNICODE;
    public static final String QUESTION = "Question";
    public static final String ANSWERS = "Answers";
    public static final String ANSWER = "Answer";
    public static final String VALUE = "Value";
    public static final String ROWS = "Rows";
    public static final String ROW = "Row";
    public static final String OPEN = "Open";
    public static final String MULTIPLE_CHOICE = "MultipleChoice";
    public static final String MULTIPLE_RESPONSE = "MultipleResponse";
    public static final String MATRIX = "Matrix";
    public static final String SCALE = "Scale";
    public static final String PRIORITY = "Priority";
    public static final String COMMENT = "Comment";
    public static final String INTRODUCTION = "Introduction";
    public static final String MULTIPLE_STEPS = "MultipleSteps";
    public static final String CHECKED = "Checked";
    public static final String ID = "ID";
    public static final String PROJECT_ID = "p";
    public static final String RESPONDENT_ID = "r";
    public static final String STYLESHEET_NAME = "ss";
    public static final String PROJECT_ID_TAG = "ProjectID";
    public static final String RESPONDENT_ID_TAG = "RespondentID";
    public static final String SESSION_ID = "SessionID";
    public static String PROLOG = "<?xml version=\"1.0\" encoding=\"" + DIALOGUE_ENCODING_SELECTED + "\"?>";
    public static final String ENVELOP = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\" SOAP:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">";
    public static final String BODY = "SOAP:Body";
    public static final String RESULT = "Result";
    public static final String TYPE = "Type";
    public static final String CRLF = "\r\n";
    public static final String DIALOGUE = "Dialogue";
    public static final String FAULT_ID = "FaultID"; //Zahid(08-10-2003)
    public static final String FAMILY = "Family";
    public static final String MESSAGE = "Message";
    public static final String RESPONDENT = "Respondent";
    public static final String GET_RESPONDENT_DIALOGUE_QUERY = "GetRespondentDialogueQuery";
    public static final String ADD_DIALOGUE_RESULT_QUERY = "AddDialogueResultQuery";
    public static final String HTTP_HEAD = "POST /objectURI HTTP/1.1\n";
    public static final String HTTP_HOST = "host:www.metatude.com\n";
    public static final String HTTP_METHOD = "SOAPMethodName: urn:develop-com:DSE#logOn\n";
    public static final String HTTP_CONTENT_LENGTH = "Content-Length:";
    public static final String MDS_HTTP_CONTENT_TYPE = "Content-Type:text/xml\n\n";
    public static final String BRSR_HTTP_CONTENT_TYPE = "text/html; charset=iso-8859-1";
    public static final String SUBMIT = "zcmdSubmit";
    public static final String EPILOGUE = "epilogue";
    public static final String RESPONSE = "Response";
    public static final String UNKNOWN_MDS_MESSAGE = "\\iYour request could not be processed properly.\\pPlease copy the entire hyperlink to the survey from your invitation e-mail and paste it in the address bar of your browser.\\i0";
    public static final String MDS_CONNECTION_MESSAGE = "\\iThe system is temporarily off-line. \\pPlease try again in a few minutes.\\pIf this error persists, please inform the coordinator of the project by sending a reply e-mail to your invitation.\\i0";

    public static final String STOP_DATE = "2001-12-01 22:17:00";
    public static final String PREF_FILE = "MCICPreferences.xml";
    public static final String EPILOG = "epilog";
    public static final String ERROR = "error";
    public static final String INTERACTIVITY = "Interactivity";
    public static final String REQUEST_NEXT = "RequestNext";
    public static final String FAULT_DESCRIPTION = "Description";
    public static final String VERSION_FILE_NAME = "comver";
    public static final String MCIC_VERSION = "MCICVersion";
    public static final String MCIC_VERSION_NO = "2.7";
    public static final String SUBMITTED = "SUBMITTED";
    public static final String UNKNOWN_ERROR = "Response";
    public static final String FIRST_SET = "FirstSet";
    public static final String CURRENT_PAGE_NO = "CurrentPageNo";
    public static final String REQUEST_BACK = "RequestBack";
    public static final String STEP_NUMBER = "StepNumber";
    public static final String TITLE = "Title";
    public static final String PAGE_NO = "PageNo";
    public static final String CURRENT_PAGE_NO_SEND_BY_MDS = "MDS didnt send";
    public static final String STYLE_SHEET = "Stylesheet";
    public static final String OK = "Ok";
    public static final String XSL_FILE_NOT_FOUND =
            "Neither your given style sheet file nor the default style sheet file is found.";
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss aaa";
    public static final String ERROR_TEMPLATE = "MCIC_ERROR";
    public static final String ERROR_FOLDER = "error";
    public static final String ERROR_FILE_NAME = "error.html";

    public static final String CONF_TAG_MDS_IP = "mdsip";
    public static final String CONF_TAG_MDS_PORT = "mdsport";
    public static final String CONF_TAG_MCIC_VERSION = "mcicVersion";
}
