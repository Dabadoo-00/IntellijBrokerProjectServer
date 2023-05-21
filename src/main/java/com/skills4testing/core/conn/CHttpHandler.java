package com.skills4testing.core.conn;

import java.io.*;

import com.skills4testing.core.soap.*;
import com.skills4testing.core.util.*;
import com.skills4testing.exchange.CVersion;

/**
* This class reads the stream from the socket and writes the output in the
* outstream to the client. It extracts http wrapper after reading from instream
* and append http wrapper while writing to out stream.
*/

public class CHttpHandler {

	/* new line character */
	private final String CRLF = "\r\n";

	// global debug on
	private boolean mDebugOn = true; //  CPreferences.debugOn;
	
	private boolean mPrintBlock = true; //  CWICTCEServer.g_app.mPrintBlock;

	/**
	 * Default constructor
	 */
	public CHttpHandler() {
	}

	/**
	 * This method receives a stream from client (MPM) and extracts http header
	 * from the XML-SOAP body.
	 * 
	 * @param inStream
	 *            input stream of the socket connection
	 * @return XML-SOAP part as string
	 */
	public String handleRequest(InputStream inStream) throws Exception {

		// InputStream in;

		// for(int i =0 ; i < 1000; i++ )
		// {
		// System.out.println("Counting number + " + i);
		// }

		CBufferedInputStream buffer;

		String body = new String();

		buffer = new CBufferedInputStream(inStream);

		String header = null;
		String contentLengthStr = null;

		/* Content-Length is extracted for the body size */
		while ((header = buffer.readLine()) != "") {
			if (header.equals(""))
				break;
			if (header.toLowerCase().startsWith("content-length")) {
				contentLengthStr = header;
			}
		}

		// Parsing for Content length
		int pos = contentLengthStr.indexOf(":");
		String intValue = contentLengthStr.substring(++pos);
		Integer integer = Integer.valueOf(intValue.trim());
		int contentLength = integer.intValue();

		// Recieving body part with out http header
		StringBuffer messageXML = new StringBuffer();
		try {
			for (int count = 0; count < contentLength; count++) {
				if (buffer.available() < 1) {
					Thread.yield();
				}
				int inByte2 = buffer.read();
				messageXML.append((char) inByte2);
				// System.out.println(messageXML.toString());
			}
		} catch (Throwable t) {
			buffer.close();
			t.printStackTrace();
		}

		body = messageXML.toString();
		// System.out.println(" Actual Message Receive from Client  : " + body
		// );

		// for(int i =0 ; i < 1000; i++ )
		// {
		// System.out.println("Counting number + " + i);
		// }
		//
		// inStream.wait();

		if (mDebugOn) {
			System.out.println("CHttpHeader> Received Query From Client :\n" + body);
		}
		buffer.close();
		return body;
	}

	/**
	 * This method appends http header to the assembled XML message and sends it
	 * to the client.
	 * 
	 * @param outStream
	 *            stream from client
	 * @param xmlPart
	 *            xml to send to the client
	 */
	public void handleResponse(OutputStream outStream, String xmlPart) {

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
			outStream.write(messageToClient.getBytes());
			outStream.close();
			outStream = null;
			if (!mPrintBlock)
				System.out.println(CVersion.kServerName + CDateTime.toSystemOutDate() + CVersion.kServerResponse );
		} catch (IOException ex) {
			if (mDebugOn) {
				System.out.println("CHttpHandler : Error> Could not sent to the client.");
			}
		}
	}

	/**
	 * This method appends http header to the assembled XML message and sends it
	 * to the client.
	 * 
	 * @param outStream
	 *            stream from client
	 * @param xmlPart
	 *            xml to send to the client
	 */
	public void handleResponse(PrintWriter output, String xmlPart) {

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
			// output.write(messageToClient.getBytes());

			output.println(messageToClient.getBytes());
			// output.close();
			// output = null;
			if (!mPrintBlock)
				System.out.println(CVersion.kServerName + CDateTime.toSystemOutDate() + CVersion.kServerResponse);
		} catch (Exception ex) {
			if (mDebugOn) {
				System.out.println("CHttpHandler : Error> Could not sent to the client.");
			}
		}
	}

}
