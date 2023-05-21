package com.skills4testing.client;

import java.io.*;
import java.net.*;

import com.skills4testing.core.util.CDateTime;
import com.skills4testing.exchange.CVersion;

public class CConnectionHandler {

	
    private boolean mDebugOn = false;
    private int mPort;
    private String mHost;

   // private final String crlf = "\r\n";
    private final String CRLF = "\r\n";


    public CConnectionHandler(String pHost, int pPort) {
        mHost = pHost;
        mPort = pPort;
    }

    /**
     * This method is used to send a the request xml of Client to Broker Simulator by calling the
     * <b>handleMessage</b>(String pRecMessage) method of <b>CMDSConnectionHandler</b>.
     * It then take the response xml from the Broker Simulator and make a <b>CMessage</b> from
     * the response xml.
     *
     * @param pConnection the <b>CMDSConnectionHandler</b> that handles the conversation
     * between MCIC and Broker Simulator.
     * @param XML the xml request from MCIC to Broker Simulator.
     *
     * @return <b>true</b> if conversation is successful otherwise <b>false</b>.
     *
     * @throws Exception
     */

    public String handleRequest(String pRequestXML) {
        boolean successFul = false;
        Socket connection = null; // isnt better to make it static member variable......
        String response = CConstants.MDS_CONNECTION_MESSAGE;

        try {
            //make connection with the Broker Simulator Server.
            connection = new Socket(mHost, mPort);

            //Send request to Broker Simulator
            sendRequest(setSoapNode(pRequestXML), connection.getOutputStream());

            //Receive response from Broker Simulator
            response = receiveResponse(connection.getInputStream());

            System.out.println(response);
            successFul = true;

        } catch (IOException ex) {
        	CClientLogger.getInstance().log(this,
                    "ERROR:In handleMessage() while establishing connection with Broker Simulator. \n\tError Message:" +
                                          ex.getMessage());
            if (mDebugOn) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException ex) {
                	CClientLogger.getInstance().log(this,
                            "ERROR:In handleRequest() while closing connection. \n\tError Message:" + ex.getMessage());
                }
            }
        }

        if (!successFul) {
            response = getErrorXML(CConstants.MDS_CONNECTION_MESSAGE, CConstants.UNKNOWN_ERROR);
        }

        return response;
    }

    /**
     * This is the main metod of this class infact for the whole application. It makes
     * the connection with Broker Simulator then add http headers with the requested message <b>pReqMessage</b>
     * to communicate with Broker Simulator and then send it to Broker Simulator.After sending it receives the response xml
     * from Broker Simulator then extract the HTTP part from the message and returns that to the the requester.
     * Modified by zahid on 07-04-2004 to support utf-8 encoding.
     *
     * @param recMessage the requested message in xml but without HTTP part.
     *
     * @return response message in xml format.
     *
     * @throws Exception
     */
    
    /**
	 * This method appends http header to the assembled XML message and sends it
	 * to the client.
	 * 
	 * @param outStream
	 *            stream from client
	 * @param xmlPart
	 *            xml to send to the client
	 */
	private boolean sendRequest(String xmlPart, OutputStream outStream) {

		boolean sentSuccess = false;
		PrintWriter writer;
		
		
		StringBuffer buffer = new StringBuffer();
		String httpHeader = new String();
		buffer.append("HTTP/1.0 200 OK");
		buffer.append(CRLF);
		buffer.append("Content-Type:text/xml");
		buffer.append(CRLF);
		buffer.append("Content-Length:");
		buffer.append(xmlPart.length());
		buffer.append(CRLF);
		buffer.append(CRLF);

		httpHeader = buffer.toString();
		String messageToClient = httpHeader + xmlPart;

		// System.out.println("CHttpHeader> Response to Client :\n" +
		// messageToClient);

		// if (debugOn) {
		// System.out.println("CHttpHeader> Response to Client :\n" +
		// messageToClient);
		// }

		try {
			 writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8")), true);
			 writer.println(messageToClient);
			//outStream.write(messageToClient.getBytes());
			//outStream.close();
			//outStream = null;
			sentSuccess = true;
			//if (!mDebugOn)
				//System.out.println(CVersion.kServerName + CDateTime.toSystemOutDate() + CVersion.kServerResponse );
		} catch (IOException ex) {
			if (mDebugOn) {
				System.out.println("CHttpHandler : Error> Could not sent to the client.");
			}
		}
		
		return sentSuccess;
	}
	
//
//    private boolean sendRequest(String pReqMessage, OutputStream pOutputStream) {
//        boolean sentSuccess = false;
//        PrintWriter writer;
//
//        try {
//            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pOutputStream, "UTF-8")), true);
//            StringBuffer httpHeaders = new StringBuffer();
//            httpHeaders.append(CConstants.HTTP_HEAD);
//            httpHeaders.append(crlf);
//            httpHeaders.append(CConstants.HTTP_HOST);
//            httpHeaders.append(crlf);
//            httpHeaders.append(CConstants.HTTP_METHOD);
//            httpHeaders.append(crlf);
//            httpHeaders.append(CConstants.HTTP_CONTENT_LENGTH);
//            httpHeaders.append(pReqMessage.length());
//            httpHeaders.append(crlf);
//            httpHeaders.append(CConstants.MDS_HTTP_CONTENT_TYPE);
//            httpHeaders.append(crlf);
//            httpHeaders.append(crlf);
//            httpHeaders.append(pReqMessage);
//            //send request message
//            writer.println(httpHeaders.toString());
//
//            if (mDebugOn) {
//                System.out.println("\nRequest to Broker Simulator = \n" + httpHeaders);
//            }
//            sentSuccess = true;
//
//        } catch (IOException ex) {
//        	CClientLogger.getInstance().log(this,
//                                          "ERROR:In sendRequest() while communicating with Broker Simulator. \n\tError Message:" +
//                                          ex.getMessage());
//            if (mDebugOn) {
//                ex.printStackTrace();
//            }
//        }
//
//        return sentSuccess;
//    }


    /**
     * This method extract the HTTP header from a xml message.
     * @param pInStream the input stream by which the response from Broker Simulator is received.
     * @return response message in xml format with HTTP header extracted.
     *
     */

    private String receiveResponse(InputStream pInStream) {
        StringBuffer messageXML = new StringBuffer();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(pInStream, "UTF-8"));

            String tmpString = "";
            char tmpChars[] = new char[2049];
            int tmpInt = -1;
            int readCount = -1;
            char newChras[];
            while ((readCount = reader.read(tmpChars)) != -1) {
                if (readCount == 0) {
                    continue;
                }
                newChras = new char[readCount];
                System.arraycopy(tmpChars, 0, newChras, 0, readCount);
                tmpString = new String(newChras);
                tmpInt = -1;
                if ((tmpInt = tmpString.toLowerCase().indexOf("content-length")) > -1) {
                    int messageSart = tmpString.indexOf("\n\r\n", tmpInt);
                    messageXML.append(tmpString.substring(messageSart));
                } else {
                    messageXML.append(tmpString);
                }
                if (messageXML.indexOf("</SOAP:Envelope>") > -1) {
                    break;
                }
                tmpChars = new char[2049];
            }
        } catch (Exception ex) {
        	CClientLogger.getInstance().log(this,
                    "ERROR:In receiveResponse() while receiving data from Broker Simulator. \n\tError Message:" + ex.getMessage());
            if (mDebugOn) {
                ex.printStackTrace();
            }
        }

        if (mDebugOn) {
            System.out.println("\nResponse from Broker Simulator = \n" + messageXML.toString());
        }
        return messageXML.toString().trim();
    }
    
    
    /**
     * This method is used make a xml message to SOAP message by adding required
     * message/element at specific position.
     *
     * @param XML the xml message that will be converted to a SOAP message.
     *
     * @return the SOAP message.
     */

    private  String setSoapNode(String pXML) {
        StringBuffer xmlResponse = new StringBuffer();
        xmlResponse.append(CConstants.PROLOG + CConstants.CRLF);
        xmlResponse.append(CConstants.ENVELOP + CConstants.CRLF);
        xmlResponse.append("<" + CConstants.BODY + ">" + CConstants.CRLF);
        xmlResponse.append(pXML);
        xmlResponse.append("</" + CConstants.BODY + ">" + CConstants.CRLF);
        xmlResponse.append("</SOAP:Envelope>");

        return xmlResponse.toString();
    }
    
    /**
     * This method create the error xml from the error string and title of the error.
     *
     * @param pEpilogString the error string.
     * @param pTitle title of the epilogue message.
     * @return the epilogue xml.
     */

    public  String getErrorXML(String pErrorString, String pTitle) {
        pErrorString = xmlEncode(pErrorString);
//     pTitle = xmlEncode(pTitle);
        return CConstants.PROLOG + "<Dialogue><Title>" + pTitle + "</Title><Error>" + pErrorString
                + "</Error></Dialogue>";
    }
    
    
    /**
     * This method tests a String(inString) and encodes special characters of  the String. If any special character(&lt;, &gt;, &, ', ")
     * exists in the String, XML parser shows an error. this method replaces these characters by
     * proper xml entities &lt;, &gt;, &amps;, &apos, &quot; respectively.
     *
     * if there is no such character it returns the String as it is
     * if the forTest is null it  returns null
     *
     * @param pInString the input string.
     *
     */

    public  String xmlEncode(String pInString) {
        String searchString[] = {"&", "<", ">", "\'", "\""};
        String replaceStartTag[] = {"&amp;", "&lt;", "&gt;", "&apos;", "&quot;"};
        int searchStringNo = 5;
        for (int i = 0; i < searchStringNo; i++) {
            pInString = pInString.replaceAll(searchString[i], replaceStartTag[i]);
        }
        return pInString;
    }
}
