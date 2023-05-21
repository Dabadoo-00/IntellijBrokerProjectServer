package com.skills4testing.exchange.order.query;

import java.util.Vector;

import org.xml.sax.SAXException;

import com.skills4testing.core.message.CMessage;
import com.skills4testing.core.message.CMessageDescriptor;
import com.skills4testing.core.message.MsgConst;

public class COrderExecuteQuery extends CMessage {

	
	/**
	 * This class contains the Login message of the clients.
	 */

	private String mBlockOrderID;
	private String mSecuritySymbol;
	private String mSecurityId;
	private String mOrderSide;
	private String mTradeQty;
	private String mOrderType;
	private String mTradePrice;
	
	//private String mRemainingOrderQty;
	
	// Constants Attribute of this class
//	private final String kExecuteOrderFamily = "ExecuteOrderFamily";
//	private final String kExecute = "Execute";
	
	private final String kBlockOrderID = "BlockOrderID"; 
	private final String kSecuritySymbol = "SecuritySymbol";
	private final String kSecurityId = "SecurityId";
	private final String kOrderSide = "OrderSide"; 
	private final String kTradeQty = "TradeQty";
	private final String kOrderType = "OrderType";
	private final String kTradePrice = "TradePrice"; 

	
	

	// Default Constructor
	public COrderExecuteQuery() throws Exception {
		setFamily(MsgConst.kExecuteOrderFamily);
		setMessageType(MsgConst.kExecute);
	}

	/**
	 * Register this message to Message Descriptor.
	 */
	@Override
	public Vector<CMessageDescriptor> registerMessages() {
		Vector<CMessageDescriptor> registerVector = new Vector<CMessageDescriptor>();
		CMessageDescriptor msgDesc = new CMessageDescriptor(mFamily, mMessageType);
		registerVector.add(msgDesc);
		return registerVector;
	}

	// Override methods of the DefaultHandler class
	// to gain notification of SAX Events and to parse the xml document.
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		
		super.endElement(namespaceURI, localName, qName);
		
		if (localName.equalsIgnoreCase(kBlockOrderID)) {
			this.setBlockOrderID(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kSecuritySymbol)) {
			this.setSecuritySymbol(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kSecurityId)) {
			this.setSecurityId(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kOrderSide)) {
			this.setOrderSide(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kTradeQty)) {
			this.setTradeQty(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kOrderType)) {
			this.setOrderType(mContents.toString());
		}
		if (localName.equalsIgnoreCase(kTradePrice)) {
			this.setTradePrice(mContents.toString());
		}
		
	}

	/**
	 * Return the XML String. for Request type message it is not essential to
	 * implement
	 */
	
	public String toXML() throws Exception {
		StringBuffer xmlResponse = new StringBuffer(super.toXML());
		xmlResponse.append("<" + kBlockOrderID + ">" + super.xmlEncode(mBlockOrderID) + "</" + kBlockOrderID + ">" + CRLF);
		xmlResponse.append("<" + kSecuritySymbol + ">" + super.xmlEncode(mSecuritySymbol) + "</" + kSecuritySymbol + ">" + CRLF);
		xmlResponse.append("<" + kSecurityId + ">" + super.xmlEncode(mSecurityId) + "</" + kSecurityId + ">" + CRLF);
		xmlResponse.append("<" + kOrderSide + ">" + super.xmlEncode(mOrderSide) + "</" + kOrderSide + ">" + CRLF);
		xmlResponse.append("<" + kTradeQty + ">" + super.xmlEncode(mTradeQty) + "</" + kTradeQty + ">" + CRLF);
		xmlResponse.append("<" + kOrderType + ">" + super.xmlEncode(mOrderType) + "</" + kOrderType + ">" + CRLF);
		xmlResponse.append("<" + kTradePrice + ">" + super.xmlEncode(mTradePrice) + "</" + kTradePrice + ">" + CRLF);
		return xmlResponse.toString();
	}

	
	
	public String getBlockOrderID() {
		return mBlockOrderID;
	}

	public void setBlockOrderID(String mBlockOrderID) {
		this.mBlockOrderID = mBlockOrderID;
	}

	public String getSecuritySymbol() {
		return mSecuritySymbol;
	}

	public void setSecuritySymbol(String mSecuritySymbol) {
		this.mSecuritySymbol = mSecuritySymbol;
	}

	public String getSecurityId() {
		return mSecurityId;
	}

	public void setSecurityId(String mSecurityId) {
		this.mSecurityId = mSecurityId;
	}

	public String getOrderSide() {
		return mOrderSide;
	}

	public void setOrderSide(String mOrderSide) {
		this.mOrderSide = mOrderSide;
	}

	public String getTradeQty() {
		return mTradeQty;
	}

	public void setTradeQty(String mTradeQty) {
		this.mTradeQty = mTradeQty;
	}

	public String getOrderType() {
		return mOrderType;
	}

	public void setOrderType(String mOrderType) {
		this.mOrderType = mOrderType;
	}

	public String getTradePrice() {
		return mTradePrice;
	}

	public void setTradePrice(String mTradePrice) {
		this.mTradePrice = mTradePrice;
	}

}
